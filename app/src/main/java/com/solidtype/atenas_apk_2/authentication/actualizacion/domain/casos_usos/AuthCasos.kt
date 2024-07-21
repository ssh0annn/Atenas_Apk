package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos

data class AuthCasos (
    val login: Login,
    val logout: Logout,
    val whoIs: WhoIs,
    val forgotPassword: ForgotPassword,
    val cambiarPassword: CambiarPassword,
    val nuevoDevice: RegistraNuevoDevice

)