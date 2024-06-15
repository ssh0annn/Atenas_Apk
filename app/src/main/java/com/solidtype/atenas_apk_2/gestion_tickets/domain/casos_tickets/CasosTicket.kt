package com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets


data class CasosTicket (
    val getTickets: GetTickets,
    val crearTicket: CrearTicket ,
    val completarPago: CompletarPago,
    val closeTicket: CloseTicket,
    val buscarTickets: buscarTickets,
)