package com.example.examapp.repos.combinedlist

import androidx.lifecycle.ViewModel
import com.example.domain.model.RepositoryModel
import com.example.domain.usecases.GetBitbucketRepositoriesUseCase
import com.example.domain.usecases.GetGithubRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    val getGithubRepositoriesUseCase: GetGithubRepositoriesUseCase,
    val getBitbucketRepositoriesUseCase: GetBitbucketRepositoriesUseCase
) : ViewModel() {

    private val mutableListRepo = mutableListOf<RepositoryModel>()

    private suspend fun initRepos() = runBlocking {

        val githubRepoList = async { getGithubRepositoriesUseCase() }
        val bitbucketRepoList = async { getBitbucketRepositoriesUseCase() }
        mutableListRepo.addAll(bitbucketRepoList.await())
        mutableListRepo.addAll(githubRepoList.await())
        mutableListRepo.shuffle()
    }

    suspend fun getRepos(): List<RepositoryModel>{
        initRepos()
        return mutableListRepo
    }
}
