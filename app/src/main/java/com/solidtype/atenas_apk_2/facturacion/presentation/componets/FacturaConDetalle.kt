package com.solidtype.atenas_apk_2.facturacion.presentation.componets


import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta

data class FacturaConDetalle(
    val factura: venta? = null,
    val detalle:List<detalle_venta> = emptyList()
)
