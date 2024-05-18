package com.solidtype.atenas_apk_2.gestion_usuarios.data.repositoryImpl

import com.solidtype.atenas_apk_2.gestion_usuarios.data.roll_usuarioDao
import com.solidtype.atenas_apk_2.gestion_usuarios.data.usuarioDao
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GestionUserRepoImpl @Inject constructor(
    private val rollDao: roll_usuarioDao,
    private val usuarioDao: usuarioDao
) : GestionUserRepository {
    override suspend fun agregarUsuario(usuario: usuario) {
        usuarioDao.addUsuario(usuario)
    }

    override fun buscarUsuario(any: String): Flow<List<usuario>> {
        return usuarioDao.getUsuarios()
    }

    override fun mosstrarUsuarios(): Flow<List<usuario>> {
        return usuarioDao.getUsuarios()
    }

    override suspend fun eliminarUsuario(usuario: usuario) {
        usuarioDao.deleteUsuario(usuario)
    }

    override suspend fun actualizarUsuario(usuario: usuario) {
        usuarioDao.updateUsuario(usuario)
    }

    override fun getRolesUsuarios(): Flow<List<roll_usuarios>> {
        return rollDao.getRollUsuarios()
    }

    override suspend fun crearRol(roll: roll_usuarios) {
        rollDao.addRollUsuario(roll)
    }
}