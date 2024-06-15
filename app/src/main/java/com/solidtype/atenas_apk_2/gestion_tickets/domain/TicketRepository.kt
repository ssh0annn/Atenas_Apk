package com.solidtype.atenas_apk_2.gestion_tickets.domain

import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import kotlinx.coroutines.flow.Flow

interface TicketRepository {

    fun getTickets(): Flow<List<ticket>>

    fun getDetallesTicket():Flow<List<detalle_ticket>>

    fun buscarTickets(any:String): Flow<List<ticket>>

    fun buscarDetalles(any:String):Flow<List<detalle_ticket>>

    suspend fun crearTicket(ticket: ticket) : detalle_ticket

    suspend fun crearDetalles(ticket:detalle_ticket)


    suspend fun closeTicket(ticket:ticket)

    suspend fun completarPagoPendiente(ticket: ticket)

}