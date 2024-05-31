package com.solidtype.atenas_apk_2.gestion_usuarios.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface usuarioDao {
    @Insert
    suspend fun addUsuario(usuario : usuario)
    @Insert
    suspend fun addUsuarios(usuario : List<usuario>)
    @Query("select * from usuario")
    fun getUsuarios(): Flow<List<usuario>>
    @Query("select * from usuario where id_roll_usuario ==:id")
    fun getUsuariosByIdRoll(id :Int): Flow<List<usuario>>
    @Query("select * from usuario where id_usuario ==:id")
    suspend fun getUsuariosById(id :Int): usuario
    @Update
    suspend fun updateUsuario(usuario : usuario)
    @Delete
    suspend fun deleteUsuario(usuario : usuario)
}