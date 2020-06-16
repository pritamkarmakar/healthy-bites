package com.healthy.bites

import android.app.Application
import com.healthy.bites.basekit.DateParser
import com.healthy.bites.basekit.ImageDownloader
import com.healthy.bites.basekit.ToastMaker
import com.healthy.bites.basekitimpl.di.BaseKitModule
import com.healthy.bites.di.AppComponent
import com.healthy.bites.di.DaggerAppComponent
import com.healthy.bites.landingscreen.di.component.ArticleListDependencies
import com.healthy.bites.landingscreen.di.component.ArticleListDependenciesProvider
import com.healthy.bites.repositorykit.HealthyBitesRepository

class MainApp : Application(), ArticleListDependenciesProvider {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        setupDI()
        super.onCreate()
    }

    private fun setupDI() {
        appComponent = DaggerAppComponent
            .builder()
            .baseKitModule(BaseKitModule(this))
            .build()
    }

    override fun dependencyProvider(): ArticleListDependencies =
        object : ArticleListDependencies {
            override fun dateParser(): DateParser = appComponent.dateParser()
            override fun toastMaker(): ToastMaker = appComponent.toastMaker()
            override fun schedulerProvider(): com.healthy.bites.basekit.SchedulerProvider =
                appComponent.schedulerProvider()

            override fun healthyBitesRepository(): HealthyBitesRepository =
                appComponent.healthyBitesRepository()

            override fun imageDownloader(): ImageDownloader = appComponent.imageDownloader()
        }
}