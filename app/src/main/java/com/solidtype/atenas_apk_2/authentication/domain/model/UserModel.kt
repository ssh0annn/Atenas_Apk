package com.solidtype.atenas_apk_2.authentication.domain.model

data class UserModel(
    val nombre : String,
    val apellido : String,
    val correo : String,
    val id_licencia : String,
    val clave : String,
    val nombre_negocio : String,
    val direccion_negocio : String,
    val telefono : String,

)