package com.solidtype.atenas_apk_2.authentication.actualizacion.domain

import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario

interface AuthRepository {
    suspend fun signing(user:String, password:String, systemID:String, licencia:String): CheckListAuth
    suspend fun signout()
    suspend fun isAutenticated(): Usuario
}