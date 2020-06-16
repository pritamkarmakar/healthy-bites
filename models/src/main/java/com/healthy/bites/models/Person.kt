package com.healthy.bites.models


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("middlename")
    val middlename: Any,
    @SerializedName("organization")
    val organization: String,
    @SerializedName("qualifier")
    val qualifier: Any,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("role")
    val role: String,
    @SerializedName("title")
    val title: Any
)