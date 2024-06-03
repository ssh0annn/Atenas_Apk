package com.solidtype.atenas_apk_2.authentication.actualizacion.data

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor():AuthRepository {
    override suspend fun signing(user: String, password: String, systemID: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signout() {
        TODO("Not yet implemented")
    }

    override suspend fun isAutenticated(): TipoUser {
        TODO("Not yet implemented")
    }
}