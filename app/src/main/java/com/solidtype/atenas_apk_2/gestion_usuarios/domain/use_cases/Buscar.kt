package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import javax.inject.Inject


class Buscar @Inject constructor(private val repo: GestionUserRepository) {

    operator fun invoke(any: String): Flow<List<Map<String, Any?>>> {
        return repo.buscarUsuario(any).map { listaUsuarios ->
            listaUsuarios.map { usuario ->
                usuario.toMap() }
        }
    }
}