package com.solidtype.atenas_apk_2.util

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


fun String.toLocalDate(formato: String = "yyyy-MM-dd"): LocalDate {
    val format= DateTimeFormatter.ofPattern(formato)

    return  LocalDate.parse(this,format)
}

fun String.toIsoDate(formato: String): String {
    val localDate = this.toLocalDate(formato)
    return localDate.toString()
}
