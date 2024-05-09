package com.solidtype.atenas_apk_2.facturacion.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface detalle_ticketDao {
    @Insert
    fun addDetalleTicket(detalleTicket : detalle_ticket)
    @Insert
    fun addDetalleTickets(detalleTicket : List<detalle_ticket>)
    @Query("select * from detalle_ticket")
    fun getDetalleTickets(): Flow<List<detalle_ticket>>
    @Query("select * from detalle_ticket where id_detalle_ticket ==:id")
    fun getDetalleTicketById(id :Int): detalle_ticket
    @Update
    fun updateDetalleTicket(detalleTicket: detalle_ticket)
    @Delete
    fun deleteDetalleTicket(detalleTicket: detalle_ticket)
}