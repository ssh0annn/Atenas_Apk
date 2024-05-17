package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import javax.inject.Inject


class Actualizar @Inject constructor(private val repo: GestionUserRepository) {

    suspend operator fun invoke(usuario: Map<String, Any?>) {
        repo.actualizarUsuario(usuario.toUsuario())
    }
}