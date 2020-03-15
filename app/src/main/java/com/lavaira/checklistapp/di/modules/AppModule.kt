package com.lavaira.checklistapp.di.modules

import com.lavaira.checklistapp.data.remote.Api
import com.lavaira.checklistapp.repository.NewsRepository
import com.lavaira.checklistapp.schedulers.SchedulerContract
import com.lavaira.checklistapp.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/****
 * Application Module which is responsible for providing the app wide instances.
 * Author: Lajesh Dineshkumar
 * Created on: 15/03/20
 * Modified on: 15/03/20
 *****/
@Module(includes = [(ViewModelModule::class)])
class AppModule {


    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : Api {
        return retrofit.create(Api::class.java)
    }


    @Provides
    @Singleton
    fun provideUserkRepository(api: Api, scheduler: SchedulerContract): NewsRepository {
        return NewsRepository(api, scheduler)
    }

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerContract {
        return SchedulerProvider()
    }
}