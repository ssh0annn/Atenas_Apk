package com.solidtype.atenas_apk_2.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible



/*
import kotlin.reflect.full.memberProperties

fun Any.toMutableMap(): MutableMap<String, Any?> {
    val map = mutableMapOf<String, Any?>()
    val kClass = this::class

    for (property in kClass.memberProperties) {
        map[property.name] = property
    }

    return map
}
*/
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

fun String.formatoActivoDDBB(): Boolean {
    return this == "Activo"
}


//conversion de cualquier data class a lista de maps
fun <T : Any> List<T>.toDataListToMapList(): List<Map<String, Any>> {
    return this.map { it.toDataClassToMap() }
}


//conversion de cualquier data class a un mapa
fun <T : Any>  T.toDataClassToMap(): Map<String, Any> {
    // Obtener todas las propiedades de la data class
    val propertiesByName = this::class.declaredMemberProperties
        .map { it.name to it }
        .toMap()

    // Construir el mapa resultante
    return propertiesByName.mapValues { (_, prop) ->
        prop.isAccessible = true
        prop.getter.call(this)!!
    }
}

//se lanza una exception IllegalArgumentException que debe controlar la funcion que la use
//si los datos en el mapa no son compatibles con la data class recibida en cuestion
//objet to dataClass

inline fun <reified T : Any> Map<String, Any?>.toDataClass(): T? {
    val constructor = T::class.primaryConstructor ?: return null
    val params = constructor.parameters

    val args = params.mapNotNull { param ->
        this[param.name]?.let { value ->
            when (param.type.classifier) {
                String::class -> value.toString()
                Int::class -> (value as? String)?.toIntOrNull() ?: value as? Int
                else -> value
            }
        }
    }.toTypedArray()

    return constructor.call(*args)
}


// funcion de time stamp a local date
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Timestamp.toLocalDate(): LocalDate {
    val instant = Instant.ofEpochSecond(seconds, nanoseconds.toLong())
    return LocalDate.ofInstant(instant, ZoneId.systemDefault())
}

//funcion de localDate a timeStamp
fun LocalDate.toTimestamp(): Timestamp {
    val instant = this.atStartOfDay(ZoneId.systemDefault()).toInstant()
    return Timestamp(Date.from(instant))
}



