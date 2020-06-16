package com.healthy.bites.basekitimpl

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.healthy.bites.basekit.ImageDownloader
import com.healthy.bites.basekit.R

class ImageDownloaderImpl(private val context: Context) : ImageDownloader {
    override fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_load)
            .into(imageView)
    }
}