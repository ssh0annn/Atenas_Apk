package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

sealed class AuthEvent {

    data class LoginEvent(val email:String, val password:String, val licencia:String = ""):AuthEvent()
    data class Recuerdame(val email:String?):AuthEvent()
    object EliminarRecuerdos:AuthEvent()

    object LogoutEvent:AuthEvent()

    object IsAutenticatedEvent:AuthEvent()

    data class ForgetPassword(val email:String):AuthEvent()

    object RegistrarNuevoDevice:AuthEvent()

    object CancelarRegistro:AuthEvent()

}