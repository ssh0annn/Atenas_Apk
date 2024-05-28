package com.solidtype.atenas_apk_2.util

import android.annotation.SuppressLint
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date


fun String.toLocalDate(formato: String = "yyyy-MM-dd"): LocalDate {
    val format = DateTimeFormatter.ofPattern(formato)
    if (this.isEmpty() || this == " ") {
        return LocalDate.now()
    }
    return LocalDate.parse(this, format)
}

fun String.toIsoDate(formato: String = "yyyy-MM-dd"): String {
    val localDate = this.toLocalDate(formato)
    return localDate.toString()
}

@SuppressLint("SimpleDateFormat")
fun Long?.formatearFecha(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    if (this == null) return ""
    return sdf.format(Date(this))
}

fun String.formatoDDBB(): String {
    val array = this.split("/")
    if (array.size != 3) return ""
    return "${array[2]}-${array[1]}-${array[0]}"
}

fun String.formatoParaUser(): String {
    val array = this.split("-")
    if (array.size != 3) return ""
    return "${array[2]}/${array[1]}/${array[0]}"
}

fun String.fomatoLocalDate(): LocalDate {
    val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return LocalDate.parse(this, format)
}

fun venta.toGetColumns(): List<String> {
    return listOf(
        "id_venta",
        "id_vendedor",
        "id_cliente",
        "id_tipo_venta",
        "subtotal",
        "impuesto",
        "total",
        "cantidad",
        "fecha",
        "estado"
    )
}

fun ListaTicket():List<String>{
    return listOf(
        "id_ticket",
        "id_vendedor",
        "id_cliente",
        "id_tipo_venta",
        "descripcion",
        "subtotal",
        "impuesto",
        "total",
        "fecha_inicio",
        "fecha_final",
        "estado"
    )
}

/**
 * param: map(string, any)
 * return: Usuario Entity
 */

fun Map<String, Any?>.toUsuario(): usuario {
    val usuario= setOf("id_roll_usuario","nombre", "apellido", "email", "clave", "telefono", "estado")
    if(this.keys.toSet() == usuario){
        return usuario(
            id_roll_usuario = this["id_roll_usuario"] as Long,
            nombre = this["nombre"] as String,
            apellido = this["apellido"] as String,
            email = this["email"] as String,
            clave = this["clave"] as String,
            telefono = this["telefono"] as String,
            estado = this["estado"] as Boolean,

            )
    }
    throw Exception("No se reconoce el mapa, favor solo usar un mapa que sea compatible con Usuario")
}
/**
 * param: map(string, any)
 * return: Usuario Entity
 */
fun usuario.toMap(): Map<String, Any?>{
    return mapOf(
        "id_roll_usuario" to id_roll_usuario,
        "nombre" to nombre,
        "apellido" to apellido,
        "email" to email,
        "clave" to clave,
        "telefono" to telefono,
        "estado" to estado
    )
}

fun LocalDate.formatoParaUser(): String {
    val format = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return this.format(format)
}

fun Boolean.formatoActivo(): String {
    return if (this) "Activo" else "Inactivo"
}