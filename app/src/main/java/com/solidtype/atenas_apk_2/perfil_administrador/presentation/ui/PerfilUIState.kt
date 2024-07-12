package com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui

import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.VerInfoAdmin
import kotlinx.coroutines.flow.Flow

data class PerfilUIState(
    val perfilAdmin: List<VerInfoAdmin?> = emptyList(),
    val mensaje:String? = "",
    val isLoading:Boolean = false,
    val passwordState:String ="",
    val isClaveCambiada:Boolean = false,
    val showDialogo:Boolean = false,

)