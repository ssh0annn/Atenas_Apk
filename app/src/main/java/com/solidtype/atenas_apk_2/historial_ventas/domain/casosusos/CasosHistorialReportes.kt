package com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos

import com.solidtype.atenas_apk_2.products.domain.userCases.SyncProductos

data class CasosHistorialReportes(
    val mostrarVentas: MostrarTodasVentas,
    val exportarVentas: ExportarVentas,
    val buscarporFechCatego: BuscarporFechCatego,

)
