package com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets

import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import javax.inject.Inject

class CompletarPago@Inject constructor(private val repo: TicketRepository)  {

    suspend operator fun invoke(ticketPagado:ticket){
        repo.completarPagoPendiente(ticketPagado)
    }
}