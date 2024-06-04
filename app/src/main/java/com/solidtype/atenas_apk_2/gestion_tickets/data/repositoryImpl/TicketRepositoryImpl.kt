package com.solidtype.atenas_apk_2.gestion_tickets.data.repositoryImpl

import com.solidtype.atenas_apk_2.gestion_tickets.data.detalle_ticketDao
import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.detalle_ticket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketRepositoryImpl @Inject constructor(
    private val miticket:ticketDao,
    private val detalle:detalle_ticketDao
):TicketRepository {
    override fun getTickets(): Flow<List<ticket>> {
        return miticket.getTickets()
    }

    override fun getDetallesTicket(): Flow<List<detalle_ticket>> {
        return detalle.getDetalleTickets()
    }

    override fun buscarTickets(any: String): Flow<List<ticket>> {
        TODO("Not yet implemented")
    }

    override fun buscarDetalles(any: String): Flow<List<detalle_ticket>> {
        TODO("Not yet implemented")
    }

    override suspend fun crearTicket(ticket: ticket): detalle_ticket {
        TODO("Not yet implemented")
    }

    override suspend fun crearDetalles(ticket: detalle_ticket) {
         detalle.addDetalleTicket(ticket)
    }

    override suspend fun closeTicket(ticket: ticket) {
        miticket.updateTicket(ticket)
    }

    override suspend fun completarPagoPendiente(ticket: ticket) {
        miticket.updateTicket(ticket)
    }
}