package com.solidtype.atenas_apk_2.gestion_usuarios.domain.repository

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import kotlinx.coroutines.flow.Flow

interface GestionUserRepository {

    suspend fun agregarUsuario(usuario: usuario)

    fun buscarUsuario(any:String): Flow<List<usuario>>
    fun mosstrarUsuarios(): Flow<List<usuario>>
    suspend fun eliminarUsuario(usuario: usuario)

    suspend fun actualizarUsuario(usuario: usuario)

    suspend fun actualizarRol(rol:roll_usuarios)

    fun getRolesUsuarios(): Flow<List<roll_usuarios>>

    suspend fun crearRol(roll: roll_usuarios)
}