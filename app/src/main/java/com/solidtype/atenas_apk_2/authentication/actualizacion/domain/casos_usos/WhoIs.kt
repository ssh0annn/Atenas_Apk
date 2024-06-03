package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import javax.inject.Inject

class WhoIs @Inject constructor(private val repo: AuthRepository) {

    suspend operator fun invoke(): TipoUser {
        return repo.isAutenticated()
    }
}