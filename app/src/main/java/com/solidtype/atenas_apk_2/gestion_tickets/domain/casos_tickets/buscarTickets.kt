package com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets

import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class buscarTickets @Inject constructor(private val repo: TicketRepository)  {

    operator fun invoke(any:String, switch: Boolean) = repo.buscarTickets(any).map { it.filter { it.ticket.estado == switch } }
}