package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repo: AuthRepository) {

    suspend operator fun invoke(email:String, password:String, systemID:String?, licencia:String)= repo.signing(email, password, systemID ?:"", licencia)

}