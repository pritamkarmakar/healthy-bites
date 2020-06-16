package com.healthy.bites.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.healthy.R
import com.healthy.bites.landingscreen.view.ArticleListActivity

class SplashActivity : AppCompatActivity() {
    private val splashTime = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // start the main activity
        Handler().postDelayed({
            launchMainActivity()
        }, splashTime)
    }

    private fun launchMainActivity() {
        val intent = Intent(this, ArticleListActivity::class.java)
        startActivity(intent)
    }
}
