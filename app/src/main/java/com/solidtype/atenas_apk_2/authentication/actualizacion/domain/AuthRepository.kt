package com.solidtype.atenas_apk_2.authentication.actualizacion.domain

interface AuthRepository {
    suspend fun signing(user:String, password:String, systemID:String):Boolean
    suspend fun signout()
    suspend fun isAutenticated():TipoUser
}