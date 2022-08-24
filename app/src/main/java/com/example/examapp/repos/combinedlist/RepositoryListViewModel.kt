package com.example.examapp.repos.combinedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.RepositoryModel
import com.example.domain.usecases.GetBitbucketRepositoriesUseCase
import com.example.domain.usecases.GetGithubRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    val getGithubRepositoriesUseCase: GetGithubRepositoriesUseCase,
    val getBitbucketRepositoriesUseCase: GetBitbucketRepositoriesUseCase
) : ViewModel() {

    private var constRepoList = mutableListOf<RepositoryModel>()

    private val _listRepo: MutableLiveData<List<RepositoryModel>> = MutableLiveData()
    val listRepo: LiveData<List<RepositoryModel>>
        get() = _listRepo

    init {
        viewModelScope.launch {
            initRepos()
        }
    }

    private suspend fun initRepos() = runBlocking {
        val githubRepoList = async { getGithubRepositoriesUseCase() }
        val bitbucketRepoList = async { getBitbucketRepositoriesUseCase() }
        constRepoList.addAll(bitbucketRepoList.await())
        constRepoList.addAll(githubRepoList.await())
        _listRepo.value = constRepoList
    }

    fun filterOnlyGithubRepos() {
        val mutableRepoList = constRepoList
        _listRepo.value = mutableRepoList.filter { repo ->
            repo.webService == "Github"
        }
    }

    fun filterOnlyBitbucketRepos() {
        val mutableRepoList = constRepoList
        _listRepo.value = mutableRepoList.filter { repo ->
            repo.webService == "Bitbucket"
        }
    }

    fun getLoadedRepos() {
        _listRepo.value = constRepoList
    }

    fun sortAlphabetically() {
        _listRepo.value = _listRepo.value?.sortedBy { repoModel ->
            repoModel.repositoryName.first().lowercaseChar()
        }
    }

    fun sortRevert() {
        _listRepo.value = _listRepo.value?.sortedByDescending { repoModel ->
            repoModel.repositoryName.first().lowercaseChar()
        }
    }
}
