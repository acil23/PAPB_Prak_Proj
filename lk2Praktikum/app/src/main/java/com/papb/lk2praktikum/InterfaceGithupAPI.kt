package com.papb.lk2praktikum

import retrofit2.http.GET
import retrofit2.http.Header

interface GithubApiService {
    @GET("user")
    suspend fun getUserProfile(
        @Header("Authorization") authHeader: String
    ): GithubUserResponse
}
