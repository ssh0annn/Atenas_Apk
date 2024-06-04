package com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets

import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import javax.inject.Inject

class CrearTicket@Inject constructor(private val repo: TicketRepository)  {

    suspend operator fun invoke(ticket:ticket) {
        ticket.id_ticket = System.currentTimeMillis()

        repo.crearDetalles(repo.crearTicket(ticket))
    }
}