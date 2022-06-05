package com.rentme.rentme.ui

import androidx.lifecycle.ViewModel
import com.rentme.rentme.managers.PrefsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val prefsManager: PrefsManager): ViewModel() {
    fun setLocalHistory(location: String){
        prefsManager.locationHistoryList.add(location)
    }

    fun getLocalHistory(): ArrayList<String>{
        return prefsManager.locationHistoryList
    }
}