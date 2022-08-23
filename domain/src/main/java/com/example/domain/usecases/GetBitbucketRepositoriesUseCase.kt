package com.example.domain.usecases

import com.example.domain.model.RepositoryModel
import com.example.domain.repository.BitbucketRepository

class GetBitbucketRepositoriesUseCase(private val bitbucketRepository: BitbucketRepository) {

    suspend operator fun invoke(): List<RepositoryModel> {
        return bitbucketRepository.getRepositories()
    }
}