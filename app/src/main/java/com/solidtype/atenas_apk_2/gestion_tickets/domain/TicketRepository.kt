package com.solidtype.atenas_apk_2.gestion_tickets.domain

import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.TicketwithRelation
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import kotlinx.coroutines.flow.Flow

interface TicketRepository {

    fun getTickets(): Flow<List<TicketwithRelation>>
    fun buscarTickets(any:String): Flow<List<TicketwithRelation>>
    suspend fun crearTicket(ticket: TicketwithRelation)
    suspend fun closeTicket(ticket:ticket)
    suspend fun completarPagoPendiente(ticket: ticket)

}