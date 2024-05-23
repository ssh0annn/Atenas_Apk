package com.solidtype.atenas_apk_2.perfil_administrador.domain.repository

import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
import kotlinx.coroutines.flow.Flow

interface PerfilAdminRepository {

    fun getAdminInfo(): Flow<List<administrador>>
    suspend fun updateAdmin(admin: administrador)

}