package com.example.fruits.presention.di

import com.example.fruits.data.repository.RemoteRepositoryImpl
import com.example.fruits.data.repository.datasource.remote.RemoteDataSource
import com.example.fruits.domain.repository.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FruitsRepositoryModule {
    @Singleton
    @Provides
    fun providesFoodRepository(
        remoteDataSource: RemoteDataSource,


        ): RemoteRepository {
        return RemoteRepositoryImpl(remoteDataSource)
    }
}