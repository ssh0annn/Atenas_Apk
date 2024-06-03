package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser

data class AuthUIStates(
    val isAutenticated: TipoUser = TipoUser.UNKNOWN,
    val isLoading:Boolean = false,
    val network:Boolean = false
)
