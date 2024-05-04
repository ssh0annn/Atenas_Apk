package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.servicio

@Dao
interface servicioDao {
    @Insert
    suspend fun addServicio(servicio : servicio)
    @Insert
    suspend fun addServicios(servicio : List<servicio>)
    @Query("select * from servicio")
    suspend fun getServicios():List<servicio>
    @Query("select * from servicio where id_servicio ==:id")
    suspend fun getServiciosById(id :Int): servicio
    @Update
    suspend fun updateServicio(servicio: servicio)
    @Delete
    suspend fun deleteServicio(servicio: servicio)
}