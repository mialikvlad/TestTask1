package com.example.data.model.bitbucket

data class BitbucketRepo(
    val name: String,
    val owner: BitbucketRepoOwner,
    val description: String?
)