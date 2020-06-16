package com.healthy.bites.models

import com.google.gson.annotations.SerializedName

data class ArticleAPIResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("response")
    val response: Response,
    @SerializedName("status")
    val status: String
)