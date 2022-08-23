package com.example.domain.usecases

import com.example.domain.model.RepositoryModel
import com.example.domain.repository.GithubRepository

class GetGithubRepositoriesUseCase(private val githubRepository: GithubRepository) {

    suspend operator fun invoke(): List<RepositoryModel> {
        return githubRepository.getRepositories()
    }
}