package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.servicio
import kotlinx.coroutines.flow.Flow

@Dao
interface servicioDao {
    @Insert
    fun addServicio(servicio : servicio)
    @Insert
    fun addServicios(servicio : List<servicio>)
    @Query("select * from servicio")
    fun getServicios(): Flow<List<servicio>>
    @Query("select * from servicio where id_servicio ==:id")
    fun getServiciosById(id :Int): servicio
    @Update
    fun updateServicio(servicio: servicio)
    @Delete
    fun deleteServicio(servicio: servicio)
}