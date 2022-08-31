package com.example.examapp.repos.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class RepositoryDetailsViewModel(
    state: SavedStateHandle
) : ViewModel() {

    private val ownerName = state.get<String>(OWNER_NAME) ?: EMPTY_VALUE
    private val repoName = state.get<String>(REPO_NAME) ?: EMPTY_VALUE
    private val webService = state.get<String>(WEB_SERVICE_NAME) ?: EMPTY_VALUE
    private val avatarUrl = state.get<String>(AVATAR_URL) ?: EMPTY_VALUE
    private val description = state.get<String>(DESCRIPTION) ?: EMPTY_VALUE

    private val _ownerNameState: MutableLiveData<String?> = MutableLiveData(ownerName)
    val ownerNameState: LiveData<String?>
        get() = _ownerNameState

    private val _repoNameState: MutableLiveData<String?> = MutableLiveData(repoName)
    val repoNameState: LiveData<String?>
        get() = _repoNameState

    private val _webServiceState: MutableLiveData<String?> = MutableLiveData(webService)
    val webServiceState: LiveData<String?>
        get() = _webServiceState

    private val _avatarUrlState: MutableLiveData<String?> = MutableLiveData(avatarUrl)
    val avatarUrlState: LiveData<String?>
        get() = _avatarUrlState

    private val _descriptionState: MutableLiveData<String?> = MutableLiveData(description)
    val descriptionState: LiveData<String?>
        get() = _descriptionState

    companion object {
        private const val OWNER_NAME = "name"
        private const val REPO_NAME = "repository"
        private const val WEB_SERVICE_NAME = "webservice"
        private const val AVATAR_URL = "avatarUrl"
        private const val DESCRIPTION = "repoDescription"
        private const val EMPTY_VALUE = "EMPTY_FIELD"
    }
}