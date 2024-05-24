package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.Dispositivo
import kotlinx.coroutines.flow.Flow

@Dao
interface DispositivoDao {
    @Insert
    suspend fun addDispositivo(dispo : Dispositivo)
    @Insert
    suspend fun addDispositivos(dispo : List<Dispositivo>)
    @Query("select * from Dispositivo")
    fun getDispositivos(): Flow<List<Dispositivo>>
    @Query("select * from Dispositivo where id_dispositivo ==:id")
    suspend fun getDispositivosById(id :Int): Dispositivo
    @Update
    suspend fun updateDispositivo(dispo: Dispositivo)
    @Delete
    suspend fun deleteDispositivo(dispo: Dispositivo)
}