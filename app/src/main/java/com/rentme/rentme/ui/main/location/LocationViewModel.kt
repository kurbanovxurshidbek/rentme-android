package com.rentme.rentme.ui.main.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentme.rentme.data.local.entity.History
import com.rentme.rentme.repository.HistoryRepository
import com.rentme.rentme.utils.UiStateList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
): ViewModel() {
    private var _locationState = MutableStateFlow<UiStateList<History>>(UiStateList.EMPTY)
    val locationState = _locationState

    fun getHistory() = viewModelScope.launch {
        _locationState.value = UiStateList.LOADING
        try {
            val historyList = historyRepository.getHistory()
            _locationState.value = UiStateList.SUCCESS(historyList)
        } catch (e: Exception){
            _locationState.value = UiStateList.ERROR(e.localizedMessage ?: "No Connection")
        }
    }

    fun saveHistory(locationName: History) = viewModelScope.launch {
            historyRepository.saveHistory(locationName)

    }
}