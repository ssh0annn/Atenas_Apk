package com.solidtype.atenas_apk_2.users.domain.userCase.severino

import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class Registrarse {
    private val repositorio:UserRepository=RepositoryImpl()


    operator fun invoke(email:String, clave:String):Boolean{
            return repositorio.signUp(email, clave)

    }

}