package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser

data class Usuario(
    val correo : String? = null,
    val tipoUser: TipoUser = TipoUser.UNKNOWN
)
