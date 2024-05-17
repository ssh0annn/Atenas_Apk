package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetRoles @Inject constructor(private val repo: GestionUserRepository) {

    operator fun invoke() = repo.getRolesUsuarios().map { rollUsuarios ->
        rollUsuarios.map {
            mapOf(
                "id_roll_usuario"  to it.id_roll_usuario,
                "nombre" to it.nombre,
                "descripcion" to it.descripcion,
                "estado" to it.estado)
        }
    }
}