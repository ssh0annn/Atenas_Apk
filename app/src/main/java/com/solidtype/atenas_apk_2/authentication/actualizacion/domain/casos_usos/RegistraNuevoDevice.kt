package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos

import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.AuthRepository
import javax.inject.Inject

class RegistraNuevoDevice @Inject constructor(private val repo: AuthRepository) {
    suspend operator fun invoke(id:String, licencia:String) {
        repo.nuevoDevice(id, licencia)
    }
}