package com.solidtype.atenas_apk_2.authentication.actualizacion.domain

import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario

interface AuthRepository {
    suspend fun signing(user:String, password:String, systemID:String, licencia:String): CheckListAuth
    suspend fun signout()
    suspend fun isAutenticated(): Usuario
    suspend fun cambiarPassword(email: String, oldPassword: String, newPassworld: String, callback:(
        Boolean, String?
    ) -> Unit)
    suspend fun olvideMiPassword(email: String): Boolean
    suspend fun nuevoDevice(id: String, licencia: String)



}