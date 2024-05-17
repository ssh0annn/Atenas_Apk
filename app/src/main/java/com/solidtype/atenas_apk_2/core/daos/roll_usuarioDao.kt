package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import kotlinx.coroutines.flow.Flow

@Dao
interface roll_usuarioDao {
    @Insert
    fun addRollUsuario(rollUsuarios : roll_usuarios)
    @Insert
    fun addRollUsuarios(rollUsuarios : List<roll_usuarios>)
    @Query("select * from roll_usuarios")
    fun getRollUsuarios(): Flow<List<roll_usuarios>>
    @Query("select * from roll_usuarios where id_roll_usuario ==:id")
    fun getRollUsuariosById(id :Int): roll_usuarios
    @Update
    fun updateRollUsuario(rollUsuarios : roll_usuarios)
    @Delete
    fun deleteRollUsuario(rollUsuarios : roll_usuarios)
}