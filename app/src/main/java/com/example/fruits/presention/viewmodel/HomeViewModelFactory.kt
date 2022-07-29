package com.example.fruits.presention.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fruits.domain.usecase.remote.GetFruitsUseCase

class HomeViewModelFactory(
    private val getFruitsUseCase: GetFruitsUseCase,
    private val app: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                app = app,
                getFruitsUseCase = getFruitsUseCase
            ) as T
        }
        throw IllegalAccessException("Unknown View Model Class ......")
    }
}