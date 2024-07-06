package com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui

import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.PerfilAdmin

sealed class PerfilEvent {

    data class UpdatePerfil(val perfil: administrador): PerfilEvent()

    object VerPerfil: PerfilEvent()

    object CleanMensaje:PerfilEvent()

    data class ChangePassword(val email:String, val oldPassword:String, val newPassword:String):PerfilEvent()

}