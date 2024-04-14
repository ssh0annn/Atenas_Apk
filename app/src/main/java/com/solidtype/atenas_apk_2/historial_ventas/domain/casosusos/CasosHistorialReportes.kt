package com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos

data class CasosHistorialReportes(
    val mostrarVentas: MostrarTodasVentas,
    val exportarVentas: ExportarVentas,
    val buscarporFechCatego: BuscarporFechCatego,
    val verTicketsPorFechas: VerTicketsPorFechas,
    val verTodosTickets: VerTodosTickets

)
