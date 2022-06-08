package com.rentme.rentme.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.data.local.entity.ModelsListEntity
import com.rentme.rentme.repository.SearchRepository
import com.rentme.rentme.utils.UiStateList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _carModels =
        MutableStateFlow<UiStateList<ModelsListEntity>>(UiStateList.EMPTY)
    val carModels = _carModels

    fun getCarModels() = viewModelScope.launch {
        _carModels.value = UiStateList.LOADING
        try {
            val response = searchRepository.getModelsListFromLocal()
            _carModels.value = UiStateList.SUCCESS(response)
        } catch (
            e: Exception
        ) {
            _carModels.value = UiStateList.ERROR(e.localizedMessage ?: "No Connection")
        }
    }

}
