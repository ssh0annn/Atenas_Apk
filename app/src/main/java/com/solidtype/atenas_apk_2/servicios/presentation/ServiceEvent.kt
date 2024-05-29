package com.solidtype.atenas_apk_2.servicios.presentation

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.servicios.modelo.servicio

sealed class ServiceEvent {
    object GetClientes : ServiceEvent()
    object GetServicios : ServiceEvent()
    object GetDispositivos : ServiceEvent()
    object GetTickets : ServiceEvent()
    object GetCurrentUser: ServiceEvent()
    data class CrearCliente(val clienteUI: Personastodas.ClienteUI) : ServiceEvent()
    data class CrearTicket(val ticket: ticket) : ServiceEvent()
    data class CreateServicio(val servicio: servicio) : ServiceEvent()
    data class CrearDispositivo(val dispositivo: Dispositivo) : ServiceEvent()

}