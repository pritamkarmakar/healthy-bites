package com.healthy.bites.models


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("docs")
    val docs: List<Article>,
    @SerializedName("meta")
    val meta: Meta
)