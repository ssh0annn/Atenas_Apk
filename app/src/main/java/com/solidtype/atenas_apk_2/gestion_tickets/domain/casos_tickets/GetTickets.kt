package com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets

import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTickets @Inject constructor(private val repo: TicketRepository) {

    operator fun invoke(switch:Boolean) = repo.getTickets().map { it.filter { it.ticket.estado == switch } }
}