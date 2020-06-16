package com.healthy.bites.landingscreen.navigator

import android.content.Context
import android.content.Intent
import com.healthy.bites.landingscreen.view.ARTICLE_URL
import com.healthy.bites.landingscreen.view.ArticleDetailsActivity

interface Navigator {
    fun launchArticleDetails(articleURL: String)
}

class NavigatorImpl(private val context: Context) : Navigator {
    override fun launchArticleDetails(articleURL: String) {
        val intent = Intent(context, ArticleDetailsActivity::class.java)
        intent.putExtra(ARTICLE_URL, articleURL)
        context.startActivity(intent)
    }
}