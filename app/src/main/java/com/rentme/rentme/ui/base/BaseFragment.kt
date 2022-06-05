package com.rentme.rentme.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.rentme.rentme.R

class BaseFragment: Fragment() {
//    fun showBottomNavigation() {
//        bottom_nav.visibility = View.VISIBLE
//    }
//
//    fun hideBottomNavigation() {
//        bottom_nav.visibility = View.GONE
//    }

    fun clearLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            requireActivity().window.statusBarColor = Color.TRANSPARENT
        }

    }

    fun setLightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            requireActivity().window.statusBarColor = Color.WHITE

            var flags: Int = requireActivity().window.decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // add LIGHT_STATUS_BAR to flag
            requireActivity().window.decorView.systemUiVisibility = flags
            requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        }
    }

    @SuppressLint("ServiceCast")
    fun closeKeyBoard() {
        val view = requireActivity().currentFocus
        if (view != null) {
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}