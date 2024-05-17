package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.usuarios

data class UserStatesUI(

    val usuarios: List<Map<String, Any?>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
