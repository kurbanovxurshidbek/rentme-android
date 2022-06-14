package com.rentme.rentme.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.model.base.BaseResponse
import com.rentme.rentme.model.base.BaseResponseList
import com.rentme.rentme.repository.SplashRepository
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashRepository: SplashRepository
) : ViewModel(){

    private val _stateModelListS = MutableStateFlow<UiStateObject<BaseResponse<BaseResponseList<String>>>>(UiStateObject.EMPTY)
    val stateModelListS = _stateModelListS

    fun getModelListS() = viewModelScope.launch {
        _stateModelListS.value = UiStateObject.LOADING
        try {
            val modelList = splashRepository.getModelsList()
            _stateModelListS.value = UiStateObject.SUCCESS(modelList)
        }catch (e : Exception){
            _stateModelListS.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")
        }
    }

    fun saveModelToLocal(modelsListEntity: ModelsListEntity) = viewModelScope.launch {
       splashRepository.saveModelsListToLocal(modelsListEntity)
    }

}