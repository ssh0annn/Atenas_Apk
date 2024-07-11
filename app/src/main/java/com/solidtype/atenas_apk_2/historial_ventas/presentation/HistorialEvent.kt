package com.solidtype.atenas_apk_2.historial_ventas.presentation

import java.time.LocalDate

sealed class HistorialEvent {

    data class GetTicketsFechas(val desde:LocalDate, val hasta:LocalDate):HistorialEvent()
    data class GetVentasFechas(val desde:LocalDate, val hasta:LocalDate):HistorialEvent()
    object GetTodosTickets:HistorialEvent()
    object Exportar:HistorialEvent()

}