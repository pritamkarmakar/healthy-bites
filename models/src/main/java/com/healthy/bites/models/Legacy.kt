package com.healthy.bites.models


import com.google.gson.annotations.SerializedName

data class Legacy(
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("thumbnailheight")
    val thumbnailheight: Int,
    @SerializedName("thumbnailwidth")
    val thumbnailwidth: Int,
    @SerializedName("xlarge")
    val xlarge: String,
    @SerializedName("xlargeheight")
    val xlargeheight: Int,
    @SerializedName("xlargewidth")
    val xlargewidth: Int
)