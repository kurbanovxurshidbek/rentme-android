package com.rentme.rentme.ui.main.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.base.BaseResponse
import com.rentme.rentme.model.base.BaseResponseObject
import com.rentme.rentme.repository.MainRepository
import com.rentme.rentme.utils.NetworkHelper
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
//    init {
//        getMainData()
//    }

    private val _homeState = MutableStateFlow<UiStateObject<BaseResponseObject<MainPage>>>(UiStateObject.EMPTY)
    val homeState = _homeState

    fun getMainData() = viewModelScope.launch {
        _homeState.value = UiStateObject.LOADING
        if (networkHelper.isNetWorkConnected()) {
            Log.d("Network", "Online")
            try {
                val mainData = mainRepository.getMainData(5)
                Log.d("Network", "ERROR - ${mainData.data.data.lastAdvertisements!!.size.toString()}")
                _homeState.value = UiStateObject.SUCCESS(mainData.data)
            } catch (e: Exception) {
                _homeState.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")
            }
        }else{
            Log.d("Network", "Offline")
//            try {
//                val mainData = mainRepository.getMainDateFromLocal()
//                _homeState.value = UiStateObject.SUCCESS(mainData)
//            }catch (e: Exception){
//                _homeState.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")
//            }

        }
    }
}