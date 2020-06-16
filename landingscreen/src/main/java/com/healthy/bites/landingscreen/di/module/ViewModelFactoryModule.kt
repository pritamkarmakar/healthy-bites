package com.healthy.bites.landingscreen.di.module

import androidx.lifecycle.ViewModelProvider
import com.healthy.bites.landingscreen.viewmodel.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}