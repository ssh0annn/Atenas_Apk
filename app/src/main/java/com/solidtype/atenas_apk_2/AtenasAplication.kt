package com.solidtype.atenas_apk_2

import android.app.Application
import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class AtenasAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val prueba : UserRepository
        prueba = RepositoryImpl()
        prueba.signUp(UserModel(null, null, "ad@live.com", null, "adderlis",
        null, null, null,
            null, null,
            null))

    }

}