package com.solidtype.atenas_apk_2.servicios.presentation.ticket

sealed class TicketEvents {

    object GetTickets:TicketEvents()
    data class BuscarTicket(val semejante:String):TicketEvents()

}