package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import javax.inject.Inject

class EditarRoll @Inject constructor(private val repo: GestionUserRepository) {

    suspend operator fun invoke(roll:roll_usuarios) = repo.actualizarRol(roll)
}