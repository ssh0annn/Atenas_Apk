package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import com.solidtype.atenas_apk_2.util.toMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class Buscar @Inject constructor(private val repo: GestionUserRepository) {

    operator fun invoke(any: String): Flow<List<usuario>> {
        return repo.buscarUsuario(any).map { data -> data.filter { it.estado } }
    }
}