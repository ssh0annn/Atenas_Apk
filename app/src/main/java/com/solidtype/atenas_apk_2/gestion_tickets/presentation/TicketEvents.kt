package com.solidtype.atenas_apk_2.gestion_tickets.presentation

import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket

sealed class TicketEvents {
    data class Search(val any:String):TicketEvents()
    data class Close(val tick:ticket):TicketEvents()
    data object Getickets:TicketEvents()
    data class UpdatePago(val pago:tipo_venta):TicketEvents()
    data class PaymentInfo(val tick:ticket):TicketEvents()
    data object Switch:TicketEvents()
}