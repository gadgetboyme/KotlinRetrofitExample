package com.gadgetboyme.coroutinesexample.api

import com.gadgetboyme.coroutinesexample.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("placeholder/user/{userId}")
    suspend fun getUser(@Path(value = "userId") userId: String) : User
}