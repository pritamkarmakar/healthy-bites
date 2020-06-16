package com.healthy.bites.landingscreen.di.component

import com.healthy.bites.basekit.DateParser
import com.healthy.bites.basekit.ImageDownloader
import com.healthy.bites.basekit.SchedulerProvider
import com.healthy.bites.basekit.ToastMaker
import com.healthy.bites.landingscreen.di.module.ArticleListModule
import com.healthy.bites.landingscreen.di.module.ViewModelFactoryModule
import com.healthy.bites.landingscreen.di.module.ViewModelsModule
import com.healthy.bites.landingscreen.view.ArticleListActivity
import com.healthy.bites.repositorykit.HealthyBitesRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [ArticleListDependencies::class], modules = [ViewModelFactoryModule::class, ViewModelsModule::class, ArticleListModule::class])
interface ArticleListActivityComponent {
    fun inject(activity: ArticleListActivity)
}

interface ArticleListDependenciesProvider{
    fun dependencyProvider(): ArticleListDependencies
}

interface ArticleListDependencies{
    fun dateParser(): DateParser
    fun toastMaker(): ToastMaker
    fun schedulerProvider(): SchedulerProvider
    fun healthyBitesRepository(): HealthyBitesRepository
    fun imageDownloader(): ImageDownloader
}