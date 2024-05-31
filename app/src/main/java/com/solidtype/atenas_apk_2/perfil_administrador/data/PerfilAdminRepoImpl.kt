package com.solidtype.atenas_apk_2.perfil_administrador.data

import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
import com.solidtype.atenas_apk_2.perfil_administrador.domain.repository.PerfilAdminRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PerfilAdminRepoImpl @Inject constructor(private val dao: administradorDao): PerfilAdminRepository {
    override fun getAdminInfo(): Flow<List<administrador>> {
       return dao.getAdministradores()
    }

    override suspend fun updateAdmin(admin: administrador) {
          dao.updateAdministrador(admin)
    }
}