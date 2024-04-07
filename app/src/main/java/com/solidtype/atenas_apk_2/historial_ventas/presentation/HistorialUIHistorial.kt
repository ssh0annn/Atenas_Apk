package com.solidtype.atenas_apk_2.historial_ventas.domain.repositories

import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad

data class HistorialUIState(
    val Historial: List<HistorialVentaEntidad> = listOf()

)