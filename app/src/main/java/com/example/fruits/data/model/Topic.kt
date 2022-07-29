package com.example.fruits.data.model


import com.google.gson.annotations.SerializedName

data class Topic(
    @SerializedName("covid-19")
    val covid19: Covid19,
    @SerializedName("family")
    val family: Family,
    @SerializedName("food-drink")
    val foodDrink: FoodDrink,
    @SerializedName("health")
    val health: Health,
    @SerializedName("people")
    val people: People
)