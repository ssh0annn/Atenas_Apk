package com.solidtype.atenas_apk_2.core.Transacciones.DaoTransacciones

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.Transacciones.ModeloTransacciones.TicketModeloRelation
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.TicketwithRelation
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket

@Dao
interface DaoTransacciones {
    //Funciones tickets
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarTipoVenta(tipoVenta: tipo_venta)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTicket(ticket : ticket)

    @Update
    suspend fun updateTicket(ticket: ticket)

    //Transactions tickets
    @Transaction
    @Query("SELECT * FROM ticket ")
    fun getAllTickets(): List<TicketModeloRelation>

    suspend fun crearTicket(ticketTransaction: TicketModeloRelation){
        insertarTipoVenta(ticketTransaction.tipo_venta)
        addTicket(ticketTransaction.ticket)
    }

    suspend fun deleteTicket(ticket: ticket){
        ticket.estado = false
        updateTicket(ticket)
    }

}