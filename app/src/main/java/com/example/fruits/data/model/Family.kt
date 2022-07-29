package com.example.fruits.data.model


import com.google.gson.annotations.SerializedName

data class Family(
    @SerializedName("approved_on")
    val approvedOn: String,
    @SerializedName("status")
    val status: String
)