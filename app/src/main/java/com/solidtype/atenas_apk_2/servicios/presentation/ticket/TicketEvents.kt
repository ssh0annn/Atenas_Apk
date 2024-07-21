package com.solidtype.atenas_apk_2.servicios.presentation.ticket

import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket

sealed class TicketEvents {

    object GetTickets:TicketEvents()
    data class BuscarTicket(val semejante:String):TicketEvents()

    data class GetPagoInfo(val tick: ticket):TicketEvents()
    data class Completarpago(val pago:tipo_venta):TicketEvents()
    data object Switch:TicketEvents()

}