package com.example.examapp.repos.combinedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.RepositoryModel
import com.example.domain.usecases.GetBitbucketRepositoriesUseCase
import com.example.domain.usecases.GetGithubRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    val getGithubRepositoriesUseCase: GetGithubRepositoriesUseCase,
    val getBitbucketRepositoriesUseCase: GetBitbucketRepositoriesUseCase
) : ViewModel() {

    private var responseRepoList = mutableListOf<RepositoryModel>()

    private val _listRepo: MutableLiveData<List<RepositoryModel>> = MutableLiveData()
    val listRepo: LiveData<List<RepositoryModel>>
        get() = _listRepo

    private val _circleProgressState: MutableLiveData<Boolean> = MutableLiveData()
    val circleProgressState: LiveData<Boolean>
        get() = _circleProgressState

    init {
        _circleProgressState.value = true
        viewModelScope.launch {
            initRepos()
        }
    }

    private suspend fun initRepos() = withContext(Dispatchers.IO) {
        responseRepoList = mutableListOf()
        val githubRepoList = async { getGithubRepositoriesUseCase() }
        val bitbucketRepoList = async { getBitbucketRepositoriesUseCase() }
        responseRepoList.addAll(bitbucketRepoList.await())
        responseRepoList.addAll(githubRepoList.await())
        _circleProgressState.postValue(false)
        _listRepo.postValue(responseRepoList)
    }

    fun onShowGithubReposClicked() {
        val mutableRepoList = responseRepoList
        _listRepo.value = mutableRepoList.filter { repo ->
            repo.webService == GITHUB_SERVICE
        }
    }

    fun onShowBitbucketReposClicked() {
        val mutableRepoList = responseRepoList
        _listRepo.value = mutableRepoList.filter { repo ->
            repo.webService == BITBUCKET_SERVICE
        }
    }

    fun onAllReposClicked() {
        _listRepo.value = responseRepoList
    }

    fun onSortAlphabeticallyClicked() {
        _listRepo.value = _listRepo.value?.sortedBy { repoModel ->
            repoModel.repositoryName.first().lowercaseChar()
        }
    }

    fun onSortRevertClicked() {
        _listRepo.value = _listRepo.value?.sortedByDescending { repoModel ->
            repoModel.repositoryName.first().lowercaseChar()
        }
    }

    fun onQueryChanged(query: String) {
        _listRepo.value = responseRepoList.filter { repositoryModel ->
            repositoryModel.ownerName.lowercase(Locale.ROOT).contains(query) ||
                    repositoryModel.repositoryName.lowercase(Locale.ROOT).contains(query) ||
                    repositoryModel.webService.lowercase(Locale.ROOT).contains(query)
        }
    }

    suspend fun onListRefreshed() {
        initRepos()
    }

    companion object {
        private const val GITHUB_SERVICE = "Github"
        private const val BITBUCKET_SERVICE = "Bitbucket"
    }
}
