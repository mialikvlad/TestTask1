package com.example.domain.repository

import com.example.domain.model.RepositoryModel

interface GithubRepository {

    suspend fun getRepositories(): List<RepositoryModel>
}