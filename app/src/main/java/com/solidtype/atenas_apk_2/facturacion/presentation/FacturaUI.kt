package com.solidtype.atenas_apk_2.facturacion.presentation

import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.FacturaConDetalle
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta

data class  FacturaUI(
    val isLoading: Boolean = false,
    val mensaje: String="",
    val facturaConDetalle: List<FacturaConDetalle?> = emptyList(),

)

