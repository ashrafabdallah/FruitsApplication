package com.example.fruits.data.api


import com.example.fruits.BuildConfig
import com.example.fruits.data.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FruitsApi {

//    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
//    @GET("/photos")
//    suspend fun getAllImages(
//        @Query("page") page: Int,
//        @Query("per_page") per_page: Int
//    ): List<UnsplashImage>
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun fruitsImages(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ResponseData>
}