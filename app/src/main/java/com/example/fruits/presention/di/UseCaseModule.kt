package com.example.fruits.presention.di

import com.example.fruits.domain.repository.remote.RemoteRepository
import com.example.fruits.domain.usecase.remote.GetFruitsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesGetRandomMealUseCase(remoteRepository: RemoteRepository): GetFruitsUseCase {
        return GetFruitsUseCase(remoteRepository)
    }
}