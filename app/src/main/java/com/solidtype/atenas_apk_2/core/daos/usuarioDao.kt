package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.usuario

@Dao
interface usuarioDao {
    @Insert
    suspend fun addUsuario(usuario : usuario)
    @Insert
    suspend fun addUsuarios(usuario : List<usuario>)
    @Query("select * from usuario")
    suspend fun getUsuarios():List<usuario>
    @Query("select * from usuario where id_usuario ==:id")
    suspend fun getUsuariosById(id :Int): usuario
    @Update
    suspend fun updateUsuario(usuario : usuario)
    @Delete
    suspend fun deleteUsuario(usuario : usuario)
}