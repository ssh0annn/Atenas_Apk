package com.solidtype.atenas_apk_2.gestion_usuarios.presentation

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario

data class UserStatesUI(
    val usuarios: List<usuario> = emptyList(),
    val isLoading: Boolean = false,
    val razones: String? = null,
    val roles: List<roll_usuarios> = emptyList(),
    val rolSelecionado: roll_usuarios? = null,
    val qr: String? = "",
    val switch:Boolean = true
)
