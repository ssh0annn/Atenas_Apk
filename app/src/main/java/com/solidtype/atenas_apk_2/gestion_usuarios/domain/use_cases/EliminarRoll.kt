package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import javax.inject.Inject

class EliminarRoll @Inject constructor(private val repo: GestionUserRepository) {

    suspend operator fun invoke(rol: roll_usuarios){
        try{
           repo.eliminarRol(rol)
        }catch (_:Exception){
            throw Exception("bobo")
        }
    }
}