package com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos

import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
import com.solidtype.atenas_apk_2.perfil_administrador.domain.repository.PerfilAdminRepository
import javax.inject.Inject

class UpdateAdmin @Inject constructor(private val repo: PerfilAdminRepository) {

    suspend operator fun invoke(perfil: administrador) = repo.updateAdmin(perfil)

}