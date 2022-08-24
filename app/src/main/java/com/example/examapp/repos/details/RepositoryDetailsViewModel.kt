package com.example.examapp.repos.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class RepositoryDetailsViewModel(
    state: SavedStateHandle
) : ViewModel() {

    private val ownerName = state.get<String>("name") ?: ""
    private val repoName = state.get<String>("repository") ?: ""
    private val webService = state.get<String>("webservice") ?: ""
    private val avatarUrl = state.get<String>("avatarUrl") ?: ""
    private val description = state.get<String>("repoDescription") ?: ""

    fun getOwnerName() = ownerName
    fun getRepoName() = repoName
    fun getWebService() = webService
    fun getAvatarUrl() = avatarUrl
    fun getDescription() = description
}