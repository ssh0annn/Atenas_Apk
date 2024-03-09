package com.solidtype.atenas_apk_2.users.data.repository

import com.solidtype.atenas_apk_2.users.data.remote.remoteFirebase
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class RepositoryImpl(val auth : remoteFirebase) : UserRepository {
    override fun signUp(user: Any?) {

    }

    override fun capturarDatos(user: Any?) {

    }

    override fun SignIn(user: Any?): Boolean? {
        return true
    }

    override fun signout(): Boolean? {
        return true
    }

    override fun getCurrentUser(): String? {
        return null
    }
}