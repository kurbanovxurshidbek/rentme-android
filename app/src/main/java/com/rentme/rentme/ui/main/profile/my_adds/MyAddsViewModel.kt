package com.rentme.rentme.ui.main.profile.my_adds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.model.base.BaseResponse
import com.rentme.rentme.model.base.BaseResponseList
import com.rentme.rentme.model.base.BaseResponseObject
import com.rentme.rentme.model.filtermodel.Advertisement
import com.rentme.rentme.repository.MyAddRepository
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAddsViewModel @Inject constructor(
    private val myAddRepository: MyAddRepository
) : ViewModel(){

    private val _stateMyAddList = MutableStateFlow<UiStateObject<BaseResponse<BaseResponseList<Advertisement>>>>(UiStateObject.EMPTY)
    val stateMyAddList = _stateMyAddList

    fun getMyAddList() = viewModelScope.launch {
        _stateMyAddList.value = UiStateObject.LOADING
        try {
            val myAddList = myAddRepository.getMyAdvertisementList()
            _stateMyAddList.value = UiStateObject.SUCCESS(myAddList)
        }catch (e: Exception){
            _stateMyAddList.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")
        }
    }

    private val _stateDeleteA = MutableSharedFlow<UiStateObject<BaseResponse<BaseResponseObject<Boolean>>>>()
    val stateDeleteA = _stateDeleteA.asSharedFlow()

    fun deleteAdvertisement(id: Int) = viewModelScope.launch {
        repeat(1){
            _stateDeleteA.emit(UiStateObject.LOADING)
            try {
                val deleteResp = myAddRepository.deleteAdvertisement(id)
                _stateDeleteA.emit(UiStateObject.SUCCESS(deleteResp))
            }catch (e: Exception){
                _stateDeleteA.emit(UiStateObject.ERROR(e.localizedMessage ?: "No Connection"))
            }
        }
        // delay(2000L)
    }

}