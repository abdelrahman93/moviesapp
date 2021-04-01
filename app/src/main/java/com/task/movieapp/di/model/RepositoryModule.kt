package com.task.movieapp.di.module

import android.content.Context
import com.task.movieapp.data.repository.RepositoryImp
import com.task.movieapp.domain.remote.FlickerAPI
import com.task.movieapp.domain.repository.Repository
import com.task.movieapp.network.Services
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun createRetrofit(context: Context) = Services.getInstance("https://api.edamam.com/").retrofit



    @Provides
    fun provideApi( retrofit: Retrofit): FlickerAPI {
        return retrofit.create(FlickerAPI::class.java)
    }


    @Provides
    fun provideRepository(flickerAPI: FlickerAPI): Repository {
        return RepositoryImp(flickerAPI = flickerAPI)
    }


}