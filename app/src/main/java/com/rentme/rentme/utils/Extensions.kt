package com.rentme.rentme.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Extensions {
    @SuppressLint("SimpleDateFormat")
    fun toTimestamp(date: String): Long{

        val timestamp = SimpleDateFormat("yyyy-MM-DD hh:mm:ss").parse(date)
        return timestamp!!.time
    }

    @SuppressLint("SimpleDateFormat")
    fun toDateFromTimestamp(timestamp: Long): String{
        val mFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.ENGLISH)
        return mFormat.format(Date(timestamp))
    }
}