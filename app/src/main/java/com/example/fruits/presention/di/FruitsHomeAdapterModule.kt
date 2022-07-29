package com.example.fruits.presention.di

import com.example.fruits.presention.adapter.FruitsHomeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FruitsHomeAdapterModule {
    @Singleton
    @Provides
    fun provideFruitsHomeAdapter(): FruitsHomeAdapter {
        return FruitsHomeAdapter()
    }
}