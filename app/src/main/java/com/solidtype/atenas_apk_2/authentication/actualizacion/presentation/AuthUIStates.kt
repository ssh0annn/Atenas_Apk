package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario

data class AuthUIStates(
    val isAutenticated: Usuario? = null,
    val isLoading:Boolean = true,
    val network:Boolean = false,
    val razones: String? = null,
    val correoGuardado: String? = "",
    val licenciaGuardada: Boolean = false,
    val enviado:Boolean = false,
    val dispositivo:Boolean = true

)
