package com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui

import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.VerInfoAdmin
import kotlinx.coroutines.flow.Flow

data class PerfilUIState(
    val perfilAdmin: List<VerInfoAdmin?> = emptyList(),
    val error:String? = "",
    val isLoading:Boolean = false

)