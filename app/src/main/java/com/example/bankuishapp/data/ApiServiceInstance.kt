package com.example.bankuishapp.data

import com.example.bankuishapp.representation.model.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServiceInstance {
    @GET("repositories")
    suspend fun getAllGitRepos(@Query("q") language:String,
                               @Query("per_page") per_page:Int,
                               @Query("page") page:Int): Response<ResponseApi>

    @GET("")
    suspend fun getRepoGitDetail()
}