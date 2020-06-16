package com.healthy.bites.basekitimpl.di

import android.content.Context
import com.healthy.bites.basekit.DateParser
import com.healthy.bites.basekit.ImageDownloader
import com.healthy.bites.basekit.SchedulerProvider
import com.healthy.bites.basekit.ToastMaker
import com.healthy.bites.basekitimpl.DateParserImpl
import com.healthy.bites.basekitimpl.ImageDownloaderImpl
import com.healthy.bites.basekitimpl.SchedulerProviderImpl
import com.healthy.bites.basekitimpl.ToastMakerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseKitModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

    @Singleton
    @Provides
    fun provideToastMaker(): ToastMaker = ToastMakerImpl(context)

    @Provides
    @Singleton
    fun provideDateParser(): DateParser = DateParserImpl()

    @Provides
    @Singleton
    fun provideImageDownloader(): ImageDownloader = ImageDownloaderImpl(context)
}