package com.example.fruits.presention.di

import com.example.fruits.data.api.FruitsApi
import com.example.fruits.data.repository.datasource.remote.RemoteDataSource
import com.example.fruits.data.repository.datasource_impl.remote.RemoteDataSourceIMPL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun providesRemoteResource(fruitsApi: FruitsApi): RemoteDataSource {
        return RemoteDataSourceIMPL(fruitsApi)
    }
}