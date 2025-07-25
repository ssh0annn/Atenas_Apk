package com.solidtype.atenas_apk_2.dispositivos.data.ddbb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import kotlinx.coroutines.flow.Flow

@Dao
interface DispositivoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDispositivo(dispo : Dispositivo)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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