package com.healthy.bites.models


import com.google.gson.annotations.SerializedName

data class Headline(
    @SerializedName("content_kicker")
    val contentKicker: Any,
    @SerializedName("kicker")
    val kicker: String,
    @SerializedName("main")
    val main: String,
    @SerializedName("name")
    val name: Any,
    @SerializedName("print_headline")
    val printHeadline: Any,
    @SerializedName("seo")
    val seo: Any,
    @SerializedName("sub")
    val sub: Any
)