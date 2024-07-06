package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import javax.inject.Inject

class CambiarPassword @Inject constructor(private val repo: AuthRepository) {

    suspend operator fun invoke(email:String, oldPassword:String, newPassword:String
    , callback:(
            Boolean, String?
        ) -> Unit) = repo.cambiarPassword(email, oldPassword, newPassword){success, reason ->
            callback(success, reason)
        }

}