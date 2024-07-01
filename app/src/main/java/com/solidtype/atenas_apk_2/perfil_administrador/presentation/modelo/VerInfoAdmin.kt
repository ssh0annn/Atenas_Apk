package com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo

import java.time.LocalDate

data class VerInfoAdmin(
    val id_administrador :Long = 5000,
    val nombre :String,
    val apellido :String,
    val correo :String?,
    val telefono:String,
    val clave :String?,
    val direccion_negocio :String,
    val nombre_negocio :String,
    val licencia :String?,
    val fecha_compra : LocalDate?,
    val fecha_caduca : LocalDate?,
    val estado :Boolean
)
