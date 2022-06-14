package com.rentme.rentme.ui.main.profile.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.model.FavouriteModel
import com.rentme.rentme.model.base.BaseResponse
import com.rentme.rentme.model.base.BaseResponseList
import com.rentme.rentme.repository.FavouriteRepository
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) : ViewModel() {

    private val _favouriteModels =
        MutableStateFlow<UiStateObject<BaseResponse<BaseResponseList<FavouriteModel>>>>(UiStateObject.EMPTY)
    val favouriteModels = _favouriteModels

    fun getFavouriteModels() = viewModelScope.launch {
        _favouriteModels.value = UiStateObject.LOADING
        try {
            val response = favouriteRepository.getFavouriteModelsFromServer()
            _favouriteModels.value = UiStateObject.SUCCESS(response)
        } catch (
            e: Exception
        ) {
            _favouriteModels.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")
        }
    }

}