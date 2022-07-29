package com.example.fruits.data.model


import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)