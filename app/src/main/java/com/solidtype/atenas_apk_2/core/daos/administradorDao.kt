package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.administrador
import kotlinx.coroutines.flow.Flow

@Dao
interface administradorDao {
    @Insert
    fun addAdministrador(admin : administrador)
    @Insert
    fun addAdministradores(admin : List<administrador>)
    @Query("select * from administrador")
    fun getAdministradores(): Flow<List<administrador>>
    @Query("select * from administrador where id_administrador ==:id")
    fun getAdministradoresById(id :Int): administrador
    @Update
    fun updateAdministrador(admin: administrador)
    @Delete
    fun deleteAdministrador(admin: administrador)
}