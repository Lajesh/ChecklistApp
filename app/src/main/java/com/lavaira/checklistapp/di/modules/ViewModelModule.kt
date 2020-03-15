package com.lavaira.checklistapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lavaira.checklistapp.architecture.ViewModelFactory
import com.lavaira.checklistapp.di.key.ViewModelKey
import com.lavaira.checklistapp.view.fragment.landing.LandingViewModel
import com.lavaira.checklistapp.view.fragment.news.NewsListViewModel
import com.lavaira.checklistapp.view.fragment.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/****
 * View Model module which provides the viewmodelfactory and viewmodel instances
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    fun bindNewsListViewModel(newsListViewModel: NewsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LandingViewModel::class)
    fun bindLandingViewModel(landingViewModel: LandingViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}