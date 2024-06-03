package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario

data class AuthUIStates(
    val isAutenticated: Usuario? = null,
    val isLoading:Boolean = false,
    val network:Boolean = false,
    val razones: String? = ""
)
