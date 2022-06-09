package com.rentme.rentme.ui.main.filter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.model.FilterPage
import com.rentme.rentme.model.MainPage
import com.rentme.rentme.model.filtermodel.Filter
import com.rentme.rentme.repository.FilterRepository
import com.rentme.rentme.repository.MainRepository
import com.rentme.rentme.utils.NetworkHelper
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

    private val _filterState = MutableStateFlow<UiStateObject<Filter>>(UiStateObject.EMPTY)
    val filterState = _filterState

    fun getFilterResult(filterPage: FilterPage) = viewModelScope.launch {
        _filterState.value = UiStateObject.LOADING

        Log.d("Network", "Online")
        try {
            val filterData = filterRepository.getFilterResult(filterPage)
            _filterState.value = UiStateObject.SUCCESS(filterData)
        } catch (e: Exception) {
            _filterState.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")

        }
    }
}