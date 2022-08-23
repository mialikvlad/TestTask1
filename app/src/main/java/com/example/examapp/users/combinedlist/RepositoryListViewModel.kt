package com.example.examapp.users.combinedlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.RepositoryModel
import com.example.domain.usecases.GetBitbucketRepositoriesUseCase
import com.example.domain.usecases.GetGithubRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    val getGithubRepositoriesUseCase: GetGithubRepositoriesUseCase,
    val getBitbucketRepositoriesUseCase: GetBitbucketRepositoriesUseCase
) : ViewModel() {

}