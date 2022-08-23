package com.example.examapp.di

import com.example.domain.repository.BitbucketRepository
import com.example.domain.repository.GithubRepository
import com.example.domain.usecases.GetBitbucketRepositoriesUseCase
import com.example.domain.usecases.GetGithubRepositoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun getGithubRepositoriesUseCase(
        githubRepository: GithubRepository
    ): GetGithubRepositoriesUseCase {
        return GetGithubRepositoriesUseCase(githubRepository)
    }

    @Provides
    fun getBitbucketRepositoriesUseCase(
        bitbucketRepository: BitbucketRepository
    ): GetBitbucketRepositoriesUseCase {
        return GetBitbucketRepositoriesUseCase(bitbucketRepository)
    }

}