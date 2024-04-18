package com.solidtype.atenas_apk_2.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun String.toLocalDate(formato: String = "yyyy-MM-dd"): LocalDate {
    val format= DateTimeFormatter.ofPattern(formato)
    if(this.isEmpty()){
       return  LocalDate.now()
    }

    return LocalDate.parse(this,format)
}

fun String.toIsoDate(formato: String="yyyy-MM-dd"): String {
    val localDate = this.toLocalDate(formato)
    return localDate.toString()
}