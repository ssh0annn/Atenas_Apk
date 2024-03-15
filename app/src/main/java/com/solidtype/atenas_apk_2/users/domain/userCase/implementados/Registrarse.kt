package com.solidtype.atenas_apk_2.users.domain.userCase.implementados


import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository

class Registrarse {
    private val repositorio: UserRepository = RepositoryImpl()



    suspend operator fun invoke(
        email: String, clave: String, name: String,
        apellido: String, nnegocio: String,
        dnegocio: String, telefono: String
    ): Boolean {
        return repositorio.signUp(email, clave, name, apellido, nnegocio, dnegocio, telefono)

    }

}






