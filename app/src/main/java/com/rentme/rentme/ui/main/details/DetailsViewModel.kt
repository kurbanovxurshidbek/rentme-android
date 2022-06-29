package com.rentme.rentme.ui.main.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.model.base.BaseResponse
import com.rentme.rentme.model.base.BaseResponseObject
import com.rentme.rentme.repository.DetailsRepository
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel(){

    private val _stateDeleteA = MutableSharedFlow<UiStateObject<BaseResponse<BaseResponseObject<Boolean>>>>()
    val stateDeleteA = _stateDeleteA

    fun deleteAdvertisement(id: Int) = viewModelScope.launch {
        repeat(1){
            _stateDeleteA.emit(UiStateObject.LOADING)
            try {
                val deleteResp = detailsRepository.deleteAdvertisement(id)
                _stateDeleteA.emit(UiStateObject.SUCCESS(deleteResp))
            }catch (e: Exception){
                _stateDeleteA.emit(UiStateObject.ERROR(e.localizedMessage ?: "No Connection"))
            }
        }
        // delay(2000L)
    }

}