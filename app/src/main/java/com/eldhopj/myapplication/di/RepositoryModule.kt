package com.eldhopj.myapplication.di

import com.eldhopj.myapplication.data.remote.handler.EveryThingApiHandler
import com.eldhopj.myapplication.data.repositories.EveryThingApiRepo
import com.eldhopj.myapplication.data.repositories.EveryThingApiRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Repository module
 *
 * @constructor Create empty Repository module
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideEverythingRepo(everyThingApiHandler: EveryThingApiHandler): EveryThingApiRepo =
        EveryThingApiRepoImpl(everyThingApiHandler)
}
