package com.example.data.model.bitbucket

import com.google.gson.annotations.SerializedName

data class BitbucketRepoOwner(
    @SerializedName("nickname")
    val name: String,
    val links: BitbucketOwnerLinks
)