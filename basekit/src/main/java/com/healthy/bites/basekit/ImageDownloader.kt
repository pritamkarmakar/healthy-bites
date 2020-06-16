package com.healthy.bites.basekit

import android.widget.ImageView

interface ImageDownloader {
    fun loadImage(imageUrl: String, imageView: ImageView)
}