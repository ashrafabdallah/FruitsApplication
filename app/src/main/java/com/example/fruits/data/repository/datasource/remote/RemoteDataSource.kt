package com.example.fruits.data.repository.datasource.remote

import com.example.fruits.data.model.ResponseData
import com.example.fruits.data.model.Result
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getFruitsImages( query: String,page:Int,perPage: Int): Response<ResponseData>
}