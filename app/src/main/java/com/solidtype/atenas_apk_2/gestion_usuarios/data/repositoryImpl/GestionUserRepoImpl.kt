package com.solidtype.atenas_apk_2.gestion_usuarios.data.repositoryImpl

import com.solidtype.atenas_apk_2.core.remote.authtentication.MetodoAutenticacion
import com.solidtype.atenas_apk_2.gestion_usuarios.data.roll_usuarioDao
import com.solidtype.atenas_apk_2.gestion_usuarios.data.usuarioDao
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository.GestionUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GestionUserRepoImpl @Inject constructor(
    private val rollDao: roll_usuarioDao,
    private val usuarioDao: usuarioDao,
    private val registro: MetodoAutenticacion
) : GestionUserRepository {
    override suspend fun agregarUsuario(usuario: usuario) {
        //Pendiende conectar con el remote para que el usuario se registre en la nube.
        try {
            registro.registerNewUsers(usuario.email, usuario.clave)
            usuarioDao.addUsuario(usuario)
            }catch (e:Exception){
            println("Error creando nuevo usuario")
            }

    }

    override fun buscarUsuario(any: String): Flow<List<usuario>> {
        //NO  esta bien... el dao no permite que le pasen un parametro semejante
        return usuarioDao.getUsuariosBySimilar(any)
    }

    override fun mosstrarUsuarios(): Flow<List<usuario>> {
        return usuarioDao.getUsuarios()
    }

    override suspend fun eliminarUsuario(usuario: usuario) {
        registro.eliminarUsuario(usuario.email, usuario.clave)
        usuarioDao.deleteUsuario(usuario)
    }

    override suspend fun actualizarUsuario(usuario: usuario) {
        usuarioDao.updateUsuario(usuario)
    }

    override suspend fun actualizarRol(rol: roll_usuarios) {
        rollDao.updateRollUsuario(rol)
    }

    override fun getRolesUsuarios(): Flow<List<roll_usuarios>> {
        return rollDao.getRollUsuarios()
    }

    override suspend fun crearRol(roll: roll_usuarios) {
        rollDao.addRollUsuario(roll)
    }

    override suspend fun eliminarRol(roll: roll_usuarios) {
        rollDao.deleteRollUsuario(roll)
    }


}