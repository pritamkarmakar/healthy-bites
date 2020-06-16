package com.healthy.bites.repositorykitimpl.repository.di

import com.healthy.bites.remote.RemoteDataSource
import com.healthy.bites.repositorykit.HealthyBitesRepository
import com.healthy.bites.repositorykitimpl.repository.HealthyBitesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HealthyBitesRepositoryModule {
    @Provides
    @Singleton
    fun provideRepositoryKit(remoteDataSource: RemoteDataSource): HealthyBitesRepository = HealthyBitesRepositoryImpl(remoteDataSource)
}