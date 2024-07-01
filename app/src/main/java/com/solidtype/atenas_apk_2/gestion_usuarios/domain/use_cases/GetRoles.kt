package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetRoles @Inject constructor(private val repo: GestionUserRepository) {


    operator fun invoke() = repo.getRolesUsuarios().map { data -> data.filter { it.estado } }
}