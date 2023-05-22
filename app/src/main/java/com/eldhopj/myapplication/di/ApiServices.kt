package com.eldhopj.myapplication.di

import com.eldhopj.myapplication.data.remote.service.EveryThingApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiServices {

    @Provides
    @Singleton
    fun provideEveryThingApiService(retrofit: Retrofit): EveryThingApiService {
        return retrofit.create(EveryThingApiService::class.java)
    }
}
