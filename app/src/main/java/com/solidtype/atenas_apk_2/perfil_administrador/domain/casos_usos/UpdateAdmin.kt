package com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos

import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.toAdministrador
import com.solidtype.atenas_apk_2.perfil_administrador.domain.repository.PerfilAdminRepository
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.PerfilAdmin
import javax.inject.Inject

class UpdateAdmin @Inject constructor(private val repo: PerfilAdminRepository) {

    suspend operator fun invoke(perfil: PerfilAdmin) = repo.updateAdmin(perfil.toAdministrador())

}