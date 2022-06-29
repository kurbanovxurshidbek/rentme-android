package com.rentme.rentme.ui.main.upload

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.data.local.entity.BrandListEntity
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.model.FileResponse
import com.rentme.rentme.model.UploadAdvertisement
import com.rentme.rentme.model.UploadAdvertisementResp
import com.rentme.rentme.repository.FeatureRepository
import com.rentme.rentme.repository.SearchRepository
import com.rentme.rentme.utils.UiStateList
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val featureRepository: FeatureRepository,
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _featureState = MutableStateFlow<UiStateObject<UploadAdvertisementResp>>(UiStateObject.EMPTY)
    val featureState = _featureState

    fun createAdvertisement(uploadAdvertisement: UploadAdvertisement) = viewModelScope.launch {
        _featureState.value = UiStateObject.LOADING
        try {
            val advertisementResp = featureRepository.createAdvertisement(uploadAdvertisement)
            _featureState.value = UiStateObject.SUCCESS(advertisementResp)
        }catch (e: Exception){
            _featureState.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")
        }
    }

    private val _fileState = MutableStateFlow<UiStateObject<FileResponse>>(UiStateObject.EMPTY)
    val fileState = _fileState

    fun createFile(files: List<MultipartBody.Part>) = viewModelScope.launch {
        _fileState.value = UiStateObject.LOADING
        try {
            val fileResp = featureRepository.createFile(files)
            _fileState.value = UiStateObject.SUCCESS(fileResp)
        }catch (e: Exception){
            _fileState.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")
        }
    }

    private val _brandListState = MutableStateFlow<UiStateList<BrandListEntity>>(UiStateList.EMPTY)
    val brandListState = _brandListState

    fun getBrandList() = viewModelScope.launch {
        _brandListState.value = UiStateList.LOADING
        try {
            val brandList = featureRepository.getBrandList()
            _brandListState.value = UiStateList.SUCCESS(brandList)
        }catch (e: Exception){
            _brandListState.value = UiStateList.ERROR(e.localizedMessage ?: "No Connection")
        }
    }

    private val _carModels = MutableSharedFlow<UiStateList<ModelsListEntity>>()
    val carModels = _carModels

    fun getCarModels() = viewModelScope.launch {
        repeat(1){
            _carModels.emit(UiStateList.LOADING)
            try {
                val response = searchRepository.getModelsListFromLocal()
                _carModels.emit(UiStateList.SUCCESS(response))
            } catch (
                e: Exception
            ) {
                _carModels.emit(UiStateList.ERROR(e.localizedMessage ?: "No Connection"))
            }
        }
    }

}