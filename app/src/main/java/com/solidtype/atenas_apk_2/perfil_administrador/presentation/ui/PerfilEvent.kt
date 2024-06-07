package com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui

import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.PerfilAdmin

sealed class PerfilEvent {

    data class UpdatePerfil(val perfil:PerfilAdmin): PerfilEvent()

    object VerPerfil: PerfilEvent()
    companion object {
        lateinit var VerPerfil: PerfilEvent
        lateinit var UpdatePerfil: PerfilEvent
    }

}