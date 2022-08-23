package com.example.domain.repository

import com.example.domain.model.RepositoryModel

interface BitbucketRepository {

    suspend fun getRepositories(): List<RepositoryModel>
}