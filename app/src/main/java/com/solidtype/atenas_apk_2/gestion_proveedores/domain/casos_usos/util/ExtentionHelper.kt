package com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.util

import com.solidtype.atenas_apk_2.gestion_proveedores.domain.modelo.persona


fun persona.toMap(): Map<String, Any?> {
    return mapOf(
        "id_persona" to this.id_persona,
        "tipo_persona" to this.tipo_persona ,
        "nombre" to this.nombre,
        "tipo_documento" to this.tipo_documento,
        "documento" to this.documento,
        "direccion" to this.direccion,
        "telefono" to this.telefono ,
        "email" to this.email ,
        "estado" to this.estado
    )
}

fun Map<String, Any?>.toPersona(nuevo: Boolean = false): persona {
    if(!nuevo) {
        return persona(
           tipo_persona =   this["tipo_persona"] as String,
           nombre =  this["nombre"] as String,
           tipo_documento =  this["tipo_documento"] as String,
            documento = this["documento"] as String,
            direccion = this["direccion"] as String?,
            telefono = this["telefono"] as String,
            email = this["email"] as String,
            estado = this["estado"] as Boolean
        )
    }
    return persona(
       id_persona =  this["id_persona"] as Long,
       tipo_persona =  this["tipo_persona"] as String,
       nombre =  this["nombre"] as String,
       tipo_documento =  this["tipo_documento"] as String,
       documento =  this["documento"] as String,
        direccion = this["direccion"] as String?,
        telefono = this["telefono"] as String,
        email = this["email"] as String,
        estado = this["estado"] as Boolean
    )


}
