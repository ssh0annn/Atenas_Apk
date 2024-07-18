package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import javax.inject.Inject

class ForgotPassword @Inject constructor(private val repo: AuthRepository) {

    suspend operator fun invoke(email:String, respuesta:(success:Boolean,cancel: Boolean,excepcion:String?)-> Unit) = repo.olvideMiPassword(email, respuesta)

}