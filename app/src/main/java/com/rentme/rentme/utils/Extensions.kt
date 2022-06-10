package com.rentme.rentme.utils

import android.annotation.SuppressLint
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
}