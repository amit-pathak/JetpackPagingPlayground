package com.playground.paging.di

import com.playground.paging.data.network.NetworkHelper
import com.playground.paging.data.services.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun providePostService(): CharacterService {
        return NetworkHelper().getRetrofit().create(CharacterService::class.java)
    }

}