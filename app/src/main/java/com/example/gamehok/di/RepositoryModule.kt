package com.example.gamehok.di

import com.example.gamehok.data.datasource.ApiService
import com.example.gamehok.data.repository.GameHokRepositoryImpl
import com.example.gamehok.data.repository.GameHokeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): GameHokeRepository {
        return GameHokRepositoryImpl(apiService)

    }
}