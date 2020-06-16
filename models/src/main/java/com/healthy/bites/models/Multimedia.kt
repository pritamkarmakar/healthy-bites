package com.healthy.bites.models


import com.google.gson.annotations.SerializedName

data class Multimedia(
    @SerializedName("caption")
    val caption: Any,
    @SerializedName("credit")
    val credit: Any,
    @SerializedName("crop_name")
    val cropName: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("legacy")
    val legacy: Legacy,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("subType")
    val subType: String,
    @SerializedName("subtype")
    val subtype: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)