package com.sankar.kotlinmvvm.retrofit

import com.sankar.kotlinmvvm.model.Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("v2/everything/")
    fun getMovieArticles(
        @Query("q") query: String?,
        @Query("apikey") apiKey: String?
    ): Call<Model?>?
}