package com.gadgetboyme.coroutinesexample.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Create a singleton retrofit builder
object RetrofitBuilder {

    const val  BASE_URL = "https://open-api.xyz/"

    val retrofitBuilder: Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: ApiService by lazy{
        retrofitBuilder.build().create(ApiService::class.java)
    }



}