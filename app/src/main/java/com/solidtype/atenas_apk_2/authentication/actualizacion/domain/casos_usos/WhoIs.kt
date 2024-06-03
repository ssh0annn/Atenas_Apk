package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario
import javax.inject.Inject

class WhoIs @Inject constructor(private val repo: AuthRepository) {

    suspend operator fun invoke(): Usuario {
        return repo.isAutenticated()
    }
}