package com.example.fruits.presention.di

import android.app.Application
import com.example.fruits.domain.usecase.remote.GetFruitsUseCase
import com.example.fruits.presention.viewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeViewModelFactoryModule {
    @Singleton
    @Provides
    fun providesHomeViewModelFactory( getFruitsUseCase: GetFruitsUseCase,
                                     app: Application
    ): HomeViewModelFactory {
        return HomeViewModelFactory(getFruitsUseCase,app)
    }
}