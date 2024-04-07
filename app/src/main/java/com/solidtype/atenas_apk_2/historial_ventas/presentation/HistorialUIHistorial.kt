package com.solidtype.atenas_apk_2.historial_ventas.presentation

import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad

data class HistorialUIState(
    val isLoading: Boolean = false,
    val Historial: List<HistorialVentaEntidad> = listOf(),
    val uriPath:String=""
)