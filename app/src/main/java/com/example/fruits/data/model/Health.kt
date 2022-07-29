package com.example.fruits.data.model


import com.google.gson.annotations.SerializedName

data class Health(
    @SerializedName("approved_on")
    val approvedOn: String,
    @SerializedName("status")
    val status: String
)