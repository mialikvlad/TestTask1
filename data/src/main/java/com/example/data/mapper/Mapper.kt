package com.example.data.mapper

import com.example.data.model.bitbucket.BitbucketRepo
import com.example.data.model.github.GithubRepo
import com.example.domain.model.RepositoryModel

private const val EMPTY_DESCRIPTION = "There is no info"
private const val GITHUB = "Github"
private const val BITBUCKET = "Bitbucket"

fun GithubRepo.toDomainModel(): RepositoryModel{
    return RepositoryModel(
        ownerAvatar = owner.avatarUrl,
        repositoryName = repositoryName,
        ownerName = owner.login,
        webService = GITHUB,
        description = description ?: EMPTY_DESCRIPTION
    )
}

fun BitbucketRepo.toDomainModel(): RepositoryModel{
    return RepositoryModel(
        ownerAvatar = reposOwner.links.avatar.href,
        repositoryName = name,
        ownerName = reposOwner.name,
        webService = BITBUCKET,
        description = description ?: EMPTY_DESCRIPTION
    )
}

