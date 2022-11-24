package com.playground.paging.di

import com.playground.paging.data.repositories.CharacterRepositoryImpl
import com.playground.paging.data.services.CharacterService
import com.playground.paging.domain.repositories.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(service: CharacterService): CharacterRepository =
        CharacterRepositoryImpl(service)
}