package com.solidtype.atenas_apk_2.historial_ventas.presentation

import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta

data class HistorialUIState(
    val isLoading: Boolean = false,
    val Historial: List<venta> = listOf(),
    val Ticket: List<ticket> = listOf(),
    val total: Double = 0.0,
    val total2: Double = 0.0,
    val uriPath:String="",
    val ventasOTicket:Boolean=false, //SI esta en false porque se esta observando
    //las ventas, si esta en true, es porque se observan los tickets.
    val error:String=""

)