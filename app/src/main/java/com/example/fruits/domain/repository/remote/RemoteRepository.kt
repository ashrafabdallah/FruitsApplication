package com.example.fruits.domain.repository.remote

import com.example.foodapp.util.Resource
import com.example.fruits.data.model.ResponseData
import com.example.fruits.data.model.Result

interface RemoteRepository {
suspend fun getFruits( query: String,page:Int,perPage: Int): Resource<ResponseData>
}