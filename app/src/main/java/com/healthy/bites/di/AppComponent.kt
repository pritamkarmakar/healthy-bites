package com.healthy.bites.di

import com.healthy.bites.basekit.DateParser
import com.healthy.bites.basekit.ImageDownloader
import com.healthy.bites.basekit.SchedulerProvider
import com.healthy.bites.basekit.ToastMaker
import com.healthy.bites.basekitimpl.di.BaseKitModule
import com.healthy.bites.remote.di.RemoteModule
import com.healthy.bites.repositorykit.HealthyBitesRepository
import com.healthy.bites.repositorykitimpl.repository.di.HealthyBitesRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseKitModule::class, HealthyBitesRepositoryModule::class, RemoteModule::class])
interface AppComponent {
    fun dateParser(): DateParser
    fun toastMaker(): ToastMaker
    fun schedulerProvider(): SchedulerProvider
    fun healthyBitesRepository(): HealthyBitesRepository
    fun imageDownloader(): ImageDownloader
}