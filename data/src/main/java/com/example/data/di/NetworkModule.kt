package com.example.data.di

import com.example.data.service.BitbucketApi
import com.example.data.service.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    @Named(GITHUB_NAME)
    fun provideGithubRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GITHUB_BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideGithubApi(@Named(GITHUB_NAME) retrofit: Retrofit): GithubApi =
        retrofit.create(GithubApi::class.java)

    @Singleton
    @Provides
    @Named(BITBUCKET_NAME)
    fun provideBitbucketRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BITBUCKET_BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideBitbucketApi(@Named(BITBUCKET_NAME) retrofit: Retrofit): BitbucketApi =
        retrofit.create(BitbucketApi::class.java)

    companion object {
        private const val GITHUB_BASE_URL = "https://api.github.com/"
        private const val BITBUCKET_BASE_URL = "https://api.bitbucket.org/2.0/"
        private const val GITHUB_NAME = "Github"
        private const val BITBUCKET_NAME = "Bitbucket"
    }
}