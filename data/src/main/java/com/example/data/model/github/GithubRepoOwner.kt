package com.example.data.model.github

import com.google.gson.annotations.SerializedName

data class GithubRepoOwner(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)