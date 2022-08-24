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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    val getGithubRepositoriesUseCase: GetGithubRepositoriesUseCase,
    val getBitbucketRepositoriesUseCase: GetBitbucketRepositoriesUseCase
) : ViewModel() {

    private var responseRepoList = mutableListOf<RepositoryModel>()
    private val queryFlow = MutableStateFlow("")

    private val _listRepo: MutableLiveData<List<RepositoryModel>> = MutableLiveData()
    val listRepo: LiveData<List<RepositoryModel>>
        get() = _listRepo

    private val _successState: MutableLiveData<Boolean> = MutableLiveData(false)
    val successState: LiveData<Boolean>
        get() = _successState

    init {
        viewModelScope.launch {
            initRepos()
        }
    }

    private suspend fun initRepos() = runBlocking {
        responseRepoList = mutableListOf()
        val githubRepoList = async { getGithubRepositoriesUseCase() }
        val bitbucketRepoList = async { getBitbucketRepositoriesUseCase() }
        responseRepoList.addAll(bitbucketRepoList.await())
        responseRepoList.addAll(githubRepoList.await())
        _listRepo.value = responseRepoList
        _successState.value = true
    }

    fun filterOnlyGithubRepos() {
        val mutableRepoList = responseRepoList
        _listRepo.value = mutableRepoList.filter { repo ->
            repo.webService == "Github"
        }
    }

    fun filterOnlyBitbucketRepos() {
        val mutableRepoList = responseRepoList
        _listRepo.value = mutableRepoList.filter { repo ->
            repo.webService == "Bitbucket"
        }
    }

    fun getLoadedRepos() {
        _listRepo.value = responseRepoList
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

    fun searchByOwnerName(query: String): Flow<String> {
        _listRepo.value = _listRepo.value?.filter { repositoryModel ->
            repositoryModel.ownerName.contains(query)
        }
        return flow { }
    }

    suspend fun reloadRepos() {
        initRepos()
    }
}
