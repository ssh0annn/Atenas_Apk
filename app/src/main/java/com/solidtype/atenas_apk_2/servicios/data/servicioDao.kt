package com.solidtype.atenas_apk_2.servicios.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import kotlinx.coroutines.flow.Flow

@Dao
interface servicioDao {
    @Insert
    suspend fun addServicio(servicio : servicio)
    @Insert
    suspend fun addServicios(servicio : List<servicio>)
    @Query("select * from servicio")
    fun getServicios(): Flow<List<servicio>>
    @Query("""SELECT * FROM servicio 
              WHERE LOWER(nombre) LIKE '%' || LOWER(:any) || '%' 
              OR LOWER(descripcion) LIKE '%' || LOWER(:any) || '%'
    """)
    fun buscarServicios(any:String):Flow<List<servicio>>

    @Query("select * from servicio where id_servicio ==:id")
    suspend fun getServiciosById(id :Int): servicio
    @Update
    suspend fun updateServicio(servicio: servicio)
    @Delete
    suspend fun deleteServicio(servicio: servicio)
}