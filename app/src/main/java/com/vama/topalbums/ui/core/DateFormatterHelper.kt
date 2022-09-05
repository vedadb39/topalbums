package com.vama.topalbums.ui.core

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"

class DateFormatterHelper @Inject constructor() {

    private val defaultFormatter = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault())

    fun getFormatterDate(dateString: String, format: String = "MMMM dd yyyy"): String {
        val date = defaultFormatter.parse(dateString)

        val dateFormat = SimpleDateFormat(format, Locale.getDefault())

        return date?.let {
            dateFormat.format(it)
        } ?: ""

    }
}
