package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

sealed class AuthEvent {

    data class LoginEvent(val email:String, val password:String ):AuthEvent()
    data class Recuerdame(val email:String?):AuthEvent()

    object LogoutEvent:AuthEvent()

    object IsAutenticatedEvent:AuthEvent()


}