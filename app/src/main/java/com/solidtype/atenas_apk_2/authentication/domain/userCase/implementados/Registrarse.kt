package com.solidtype.atenas_apk_2.authentication.domain.userCase.implementados


import com.solidtype.atenas_apk_2.authentication.domain.repository.UserRepository
import javax.inject.Inject

class Registrarse @Inject constructor(private val repositorio: UserRepository){




    suspend operator fun invoke(
        email: String, clave: String, name: String, sim: String,
        apellido: String, nnegocio: String,
        dnegocio: String, telefono: String
    ): Boolean {
        return repositorio.signUp(email, clave, name, sim, apellido, nnegocio, dnegocio, telefono)

    }

}






