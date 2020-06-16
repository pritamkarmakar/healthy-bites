package com.healthy.bites.landingscreen.di.module

import androidx.lifecycle.ViewModel
import com.healthy.bites.landingscreen.viewmodel.ArticleListViewModel
import com.healthy.bites.landingscreen.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ArticleListViewModel::class)
    abstract fun bindMainViewModel(myViewModel: ArticleListViewModel): ViewModel
}