package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import javax.inject.Inject


class CrearRoles @Inject constructor(private val repo: GestionUserRepository) {

    suspend operator fun invoke(rol: Map<String, Any?>) {
        repo.crearRol(
            roll_usuarios(
                nombre = rol["nombre"] as String,
                descripcion = rol["descripcion"] as String,
                estado = rol["estado"] as Boolean
            )
        )
    }
}