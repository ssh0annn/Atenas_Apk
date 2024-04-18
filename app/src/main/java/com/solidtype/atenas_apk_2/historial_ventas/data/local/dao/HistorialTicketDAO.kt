package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialTicketEntidad
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface HistorialTicketDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setHistorialTiket(entidad : HistorialTicketEntidad)

    @Query("SELECT * FROM HistorialTicket_Table")
    fun getHistorialTicket(): Flow<List<HistorialTicketEntidad>>

    @Query("SELECT * FROM HistorialTicket_Table WHERE ((FechaInicial >= :fechaI) AND (FechaInicial <= :fechaF))")
    fun getHistorialTicketFechaDias(fechaI :LocalDate, fechaF :LocalDate): Flow<List<HistorialTicketEntidad>>

    @Query("SELECT * FROM HistorialTicket_Table")
    fun getAllTicketsNormal(): List<HistorialTicketEntidad>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTickets(tickets: List<HistorialTicketEntidad>)
}