package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.ticket
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface ticketDao {
    @Insert
    fun addTicket(ticket : ticket)
    @Insert
    fun addTickets(ticket : List<ticket>)
    @Query("select * from ticket")
    fun getTickets(): Flow<List<ticket>>
    @Query("SELECT * FROM ticket WHERE fecha_inicio BETWEEN :fechaIni AND :fechaFin")
    fun getTicketsByFechas(fechaIni: LocalDate, fechaFin:LocalDate):Flow<List<ticket>>
    @Query("select * from ticket where id_vendedor ==:id")
    fun getTicketsByIdUsuario(id :Long): Flow<List<ticket>>
    @Query("select * from ticket where id_cliente ==:id")
    fun getTicketsByIdPersona(id :Long): Flow<List<ticket>>
    @Query("select * from ticket where id_tipo_venta ==:id")
    fun getTicketsByIdTipoVenta(id :Long): Flow<List<ticket>>
    @Query("select * from ticket where id_dispositivo ==:id")
    fun getTicketsByIdDispositivo(id :Long): Flow<List<ticket>>
    @Query("select * from ticket where id_ticket ==:id")
    fun getTicketById(id :Long): ticket
    @Update
    fun updateTicket(ticket: ticket)
    @Delete
    fun deleteTicket(ticket: ticket)
}