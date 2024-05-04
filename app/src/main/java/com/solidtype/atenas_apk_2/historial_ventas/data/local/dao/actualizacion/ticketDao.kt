package com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.ticket

@Dao
interface ticketDao {
    @Insert
    suspend fun addTicket(ticket : ticket)
    @Insert
    suspend fun addTickets(ticket : List<ticket>)
    @Query("select * from ticket")
    suspend fun getTickets():List<ticket>
    @Query("select * from ticket where id_ticket ==:id")
    suspend fun getTicketsById(id :Int): ticket
    @Update
    suspend fun updateTicket(ticket: ticket)
    @Delete
    suspend fun deleteTicket(ticket: ticket)
}