package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface ticketDao {
    @Insert
    fun addTicket(ticket : ticket)
    @Insert
    fun addTickets(ticket : List<ticket>)
    @Query("select * from ticket")
    fun getTickets(): Flow<List<ticket>>
    @Query("select * from ticket where id_ticket ==:id")
    fun getTicketsById(id :Int): ticket
    @Update
    fun updateTicket(ticket: ticket)
    @Delete
    fun deleteTicket(ticket: ticket)
}