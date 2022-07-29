package com.example.fruits.data.model


import com.google.gson.annotations.SerializedName

data class TopicSubmissions(
    @SerializedName("health")
    val health: Health
)