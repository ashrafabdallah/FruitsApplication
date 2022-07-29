package com.example.fruits.data.repository

import android.app.appsearch.SearchResult
import com.example.foodapp.util.Resource
import com.example.fruits.data.model.ResponseData
import com.example.fruits.data.repository.datasource.remote.RemoteDataSource
import com.example.fruits.domain.repository.remote.RemoteRepository
import retrofit2.Response

class RemoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {
    override suspend fun getFruits(query: String,page:Int, perPage: Int): Resource<ResponseData> {
        return responseToResultResource(remoteDataSource.getFruitsImages(query,page, perPage))

    }


    private fun responseToResultResource(response: Response<ResponseData>): Resource<ResponseData> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)

            }
        }
        return Resource.Error(response.message())
    }

}