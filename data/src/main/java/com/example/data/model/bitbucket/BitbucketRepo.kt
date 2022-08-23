package com.example.data.model.bitbucket

data class BitbucketRepo(
    val name: String,
    val reposOwner: BitbucketRepoOwner,
    val description: String?
)