package com.example.data.model.github

import com.google.gson.annotations.SerializedName

data class GithubRepo(
    @SerializedName("name")
    val repositoryName: String,
    val owner: GithubRepoOwner,
    val description: String?
)