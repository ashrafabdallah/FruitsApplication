package com.example.fruits.domain.usecase.remote

import com.example.foodapp.util.Resource
import com.example.fruits.data.model.ResponseData
import com.example.fruits.data.model.Result
import com.example.fruits.domain.repository.remote.RemoteRepository

class GetFruitsUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend fun executeGetFruitsUseCase(query: String,page:Int,perPage: Int): Resource<ResponseData>{
        return remoteRepository.getFruits(query,page,perPage)
    }
}