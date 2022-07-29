package com.example.fruits.data.repository.datasource_impl.remote

import com.example.fruits.data.api.FruitsApi
import com.example.fruits.data.model.ResponseData
import com.example.fruits.data.model.Result
import com.example.fruits.data.repository.datasource.remote.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceIMPL(private val fruitsApi: FruitsApi) : RemoteDataSource {
    override suspend fun getFruitsImages(query: String,page:Int, perPage: Int): Response<ResponseData> {
        return fruitsApi.fruitsImages(query,page,perPage)
    }

}