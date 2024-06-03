package com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser

data class CheckListAuth(
    val autenticado: Boolean = false,
    val deviceRegistrado:Boolean = false,
    val licensiaActiva: Boolean = false,
    val tipoUser: TipoUser = TipoUser.UNKNOWN,
    val emailUsuario: String? = null

)
