package com.healthy.bites.landingscreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import com.healthy.bites.landingscreen.R
import com.healthy.bites.landingscreen.databinding.ActivityArticleDetailsBinding

internal const val ARTICLE_URL = "ARTICLE_URL"

class ArticleDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityArticleDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_article_details)
        val url = intent.extras?.getString(ARTICLE_URL) ?: ""
        setupWebView(binding.webview, url)
    }

    private fun setupWebView(webview: WebView, url: String) {
        webview.settings.javaScriptEnabled = true
        webview.loadUrl(url)
    }
}