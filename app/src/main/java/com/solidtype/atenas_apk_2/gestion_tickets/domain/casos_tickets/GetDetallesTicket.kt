package com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets

import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import javax.inject.Inject

class GetDetallesTicket @Inject constructor(private val repo:TicketRepository) {
    operator fun invoke() = repo.getDetallesTicket()
}