package com.step.repolist.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun createGithubApiService(): GithubAPIService = Retrofit
    .Builder()
    .baseUrl("https://api.github.com")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(GithubAPIService::class.java)

interface GithubAPIService {
    @GET("/search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): Call<SearchResult>
}