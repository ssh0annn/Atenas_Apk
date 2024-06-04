package com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets

import com.solidtype.atenas_apk_2.gestion_tickets.domain.TicketRepository
import org.checkerframework.common.reflection.qual.Invoke
import javax.inject.Inject

class BuscarDetallesTicket @Inject constructor(private val repo:TicketRepository) {

    operator fun invoke(any:String) = repo.buscarDetalles(any)
}