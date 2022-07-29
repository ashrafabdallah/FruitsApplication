package com.example.fruits.presention.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodapp.util.Resource
import com.example.fruits.data.model.ResponseData
import com.example.fruits.domain.usecase.remote.GetFruitsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getFruitsUseCase: GetFruitsUseCase,
    private val app: Application,
) : AndroidViewModel(app) {


    var connectData: MutableLiveData<String> = MutableLiveData()

    @SuppressLint("MissingPermission")
    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    fun getConnect() {
        if (isNetworkAvailable(app)) {
            connectData.postValue("ok")
        } else {
            connectData.postValue("no")
        }
    }

    var fruitsImageMutable: MutableLiveData<Resource<ResponseData>> = MutableLiveData()
    fun getImageFruits(query:String,currentPage:Int) = viewModelScope.launch(Dispatchers.IO) {
        fruitsImageMutable.postValue(Resource.Loading())
        try {
            if (connectData.value == "ok") {

                var response = getFruitsUseCase.executeGetFruitsUseCase(query,currentPage, 10)
                fruitsImageMutable.postValue(response)
            } else {
                fruitsImageMutable.postValue(Resource.Error("Internet is not available"))
            }

        } catch (e: Exception) {
            fruitsImageMutable.postValue(Resource.Error(e.message.toString()))
        }
    }


}