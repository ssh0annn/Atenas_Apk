package com.solidtype.atenas_apk_2.gestion_tickets.data.repositoryImpl

import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val miticket:ticketDao
):TicketRepository {
    override fun getTickets(): Flow<List<ticket>> {
        return miticket.getTickets()
    }



    override fun buscarTickets(any: String): Flow<List<ticket>> {
        TODO("Not yet implemented")
    }



    override suspend fun crearTicket(ticket: ticket) {
        TODO("Not yet implemented")
    }


    override suspend fun closeTicket(ticket: ticket) {
        miticket.updateTicket(ticket)
    }

    override suspend fun completarPagoPendiente(ticket: ticket) {
        miticket.updateTicket(ticket)
    }
}