package com.rentme.rentme.ui.main.filter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.model.FilterPage
import com.rentme.rentme.model.base.BaseResponseList
import com.rentme.rentme.model.filtermodel.Advertisement
import com.rentme.rentme.repository.FilterRepository
import com.rentme.rentme.utils.UiStateList
import com.rentme.rentme.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val filterRepository: FilterRepository,
) : ViewModel() {
//    init {
//        getMainData()
//    }

    private val _filterState = MutableStateFlow<UiStateList<Advertisement>>(UiStateList.EMPTY)
    val filterState = _filterState

    fun getFilterResult(filterPage: FilterPage) = viewModelScope.launch {
        _filterState.value = UiStateList.LOADING

        try {
            val filterData = filterRepository.getFilterResult(filterPage)
            if (filterData.data.success){
                _filterState.value = UiStateList.SUCCESS(filterData.data.data)
            }else{
                _filterState.value = UiStateList.ERROR(filterData.data.error.message)
            }
         } catch (e: Exception) {
            _filterState.value = UiStateList.ERROR(e.localizedMessage ?: "No Connection")

        }
    }
}