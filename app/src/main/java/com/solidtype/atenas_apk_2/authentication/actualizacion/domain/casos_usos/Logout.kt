package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repo: AuthRepository) {

    suspend operator fun invoke() {
        repo.signout()
    }
}