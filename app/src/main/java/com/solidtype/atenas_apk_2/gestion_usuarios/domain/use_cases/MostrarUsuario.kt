package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MostrarUsuario @Inject constructor(private val repo: GestionUserRepository) {
    operator fun invoke(): Flow<List<Map<String, Any?>>> {
        return repo.mosstrarUsuarios().map { listaUsuarios ->
            listaUsuarios.map { usuario ->
                usuario.toMap() }
        }
    }
}