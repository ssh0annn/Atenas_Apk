package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialTicketEntidad
import kotlinx.coroutines.flow.Flow

@Dao
interface HistorialTicketDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setHistorialTiket(entidad : HistorialTicketEntidad)

    @Query("SELECT * FROM HistorialTicket_Table")
    fun getHistorialTicket(): Flow<List<HistorialTicketEntidad>>

    @Query("SELECT * FROM HistorialTicket_Table WHERE Fecha = :fecha AND Dias = :dias")
    fun getHistorialTicketFechaDias(fecha : String, dias : Int): Flow<List<HistorialTicketEntidad>>
}