package com.solidtype.atenas_apk_2.core.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.detalle_ticket

@Dao
interface detalle_ticketDao {
    @Insert
    suspend fun addDetalleTicket(detalleTicket : detalle_ticket)
    @Insert
    suspend fun addDetalleTickets(detalleTicket : List<detalle_ticket>)
    @Query("select * from detalle_ticket")
    suspend fun getDetalleTickets():List<detalle_ticket>
    @Query("select * from detalle_ticket where id_detalle_ticket ==:id")
    suspend fun getDetalleTicketById(id :Int):detalle_ticket
    @Update
    suspend fun updateDetalleTicket(detalleTicket: detalle_ticket)
    @Delete
    suspend fun deleteDetalleTicket(detalleTicket: detalle_ticket)
}