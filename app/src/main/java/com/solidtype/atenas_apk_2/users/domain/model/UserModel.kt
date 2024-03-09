package com.solidtype.atenas_apk_2.users.domain.model

import android.provider.ContactsContract.Data

data class uselogin(
    val nombre : String?,
    val apellido : String?,
    val correo : String?,
    val id_licencia : String?,
    val clave : String?,
    val nombre_negocio : String?,
    val direccion_negocio : String?,
    val telefono : String?,
    val estado_licencia : Boolean?,
    val fecha_finalizacion : Data?,
    val fecha_inicio : Data?
)