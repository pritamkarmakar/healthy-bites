package com.healthy.bites.landingscreen.di.module

import androidx.appcompat.app.AppCompatActivity
import com.healthy.bites.basekit.DateParser
import com.healthy.bites.basekit.ImageDownloader
import com.healthy.bites.landingscreen.adapter.HealthyBitesAdapter
import com.healthy.bites.landingscreen.adapter.HealthyBitesAdapterImpl
import com.healthy.bites.landingscreen.navigator.Navigator
import com.healthy.bites.landingscreen.navigator.NavigatorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ArticleListModule(private val activity: AppCompatActivity) {
    @Provides
    @Singleton
    fun provideHealthyBitesAdapter(
        dateParser: DateParser,
        imageDownloader: ImageDownloader,
        navigator: Navigator
    ): HealthyBitesAdapter =
        HealthyBitesAdapterImpl(dateParser, navigator, imageDownloader)

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = NavigatorImpl(activity)
}