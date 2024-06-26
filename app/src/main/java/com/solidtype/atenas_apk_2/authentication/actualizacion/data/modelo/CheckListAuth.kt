package com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser

data class CheckListAuth(
    var autenticado: Boolean = false,
    var deviceRegistrado:Boolean = false,
    var licensiaActiva: Boolean = false,
    var tipoUser: TipoUser = TipoUser.UNKNOWN,
    var emailUsuario: String? = null

)
