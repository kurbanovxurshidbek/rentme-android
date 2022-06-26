package com.rentme.rentme.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

object Extensions {
    @SuppressLint("SimpleDateFormat")
    fun toTimestamp(date: String, format: String): Long{
//       format = "dd-MM-yyyy"
        val timestamp = SimpleDateFormat(format).parse(date)
        return timestamp!!.time
    }

    @SuppressLint("SimpleDateFormat")
    fun toDateFromTimestamp(timestamp: Long, format: String): String{
//        format = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'"
        val mFormat = SimpleDateFormat(format, Locale.ENGLISH)
        return mFormat.format(Date(timestamp))
    }

    fun Context.showKeyboard() {
        val content =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        content.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    fun  Fragment.hideKeyboard(context: Context) {
        val manage =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manage.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

}