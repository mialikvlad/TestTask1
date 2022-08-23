package com.example.data.repository

import com.example.data.mapper.toDomainModel
import com.example.data.service.BitbucketApi
import com.example.domain.model.RepositoryModel
import com.example.domain.repository.BitbucketRepository

class BitbucketRepositoryImpl(
    private val bitbucketApi: BitbucketApi
) : BitbucketRepository{

    override suspend fun getRepositories(): List<RepositoryModel> {
        val response = bitbucketApi.getRepositories(VALUES)

        return if(response.isSuccessful){
            val repoList = response.body()?.values ?: throw Exception(NULL_BODY)
            repoList.map { repo ->
                repo.toDomainModel()
            }
        } else {
            throw checkErrorCode(response.code())
        }
    }

    companion object {
        private const val NULL_BODY = "Null body"
        private const val VALUES = "values.name,values.owner,values.description"
    }
}