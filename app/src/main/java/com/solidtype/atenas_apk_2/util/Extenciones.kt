package com.solidtype.atenas_apk_2.util

import android.annotation.SuppressLint
import com.google.firebase.Timestamp
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialTicketEntidad
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


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

@SuppressLint("SimpleDateFormat")
fun Long?.formatearFecha(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    if(this == null) return ""
    return sdf.format(Date(this))
}

fun String.formatoDDBB(): String {
    val array = this.split("/")
    if(array.size != 3) return ""
    return "${array[2]}-${array[1]}-${array[0]}"
}

fun String.formatoParaUser(): String {
    val array = this.split("-")
    if(array.size != 3) return ""
    return "${array[2]}/${array[1]}/${array[0]}"
}

fun String.fomatoLocalDate(): LocalDate{
    val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return LocalDate.parse(this,format)
}
