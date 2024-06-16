package com.solidtype.atenas_apk_2.gestion_tickets.domain

import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import kotlinx.coroutines.flow.Flow

interface TicketRepository {

    fun getTickets(): Flow<List<ticket>>
    fun buscarTickets(any:String): Flow<List<ticket>>
    suspend fun crearTicket(ticket: ticket)
    suspend fun closeTicket(ticket:ticket)
    suspend fun completarPagoPendiente(ticket: ticket)

}