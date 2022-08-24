package com.example.data.repository


import android.widget.Toast
import com.example.data.mapper.toDomainModel
import com.example.data.service.GithubApi
import com.example.domain.model.RepositoryModel
import com.example.domain.repository.GithubRepository

class GithubRepositoryImpl(
    private val githubApi: GithubApi
) : GithubRepository {

    override suspend fun getRepositories(): List<RepositoryModel> {
        val response = githubApi.getRepositories()

        return if (response.isSuccessful) {
            val repoList = response.body() ?: throw Exception(NULL_BODY)
            repoList.map { repo ->
                repo.toDomainModel()
            }
        } else {
            throw checkErrorCode(response.code())
            /*Toast.makeText(context, "Github is overloaded", 3000)*/
        }
    }

    companion object {
        private const val NULL_BODY = "Null body"
    }
}