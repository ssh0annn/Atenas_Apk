package com.solidtype.atenas_apk_2.historial_ventas.presentation

import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialTicketEntidad
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad

data class HistorialUIState(
    val isLoading: Boolean = false,
    val Historial: List<HistorialVentaEntidad> = listOf(),
    val Ticket: List<HistorialTicketEntidad> = listOf(),
    val total: Double = 0.0,
    val total2: Double = 0.0,
    val uriPath:String=""
)