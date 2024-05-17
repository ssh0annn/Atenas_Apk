package com.solidtype.atenas_apk_2.core.daos

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
    fun addUsuario(usuario : usuario)
    @Insert
    fun addUsuarios(usuario : List<usuario>)
    @Query("select * from usuario")
    fun getUsuarios(): Flow<List<usuario>>
    @Query("select * from usuario where id_usuario ==:id")
    fun getUsuariosById(id :Int): usuario
    @Update
    fun updateUsuario(usuario : usuario)
    @Delete
    fun deleteUsuario(usuario : usuario)
}