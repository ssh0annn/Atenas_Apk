package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.servicios.modelo.servicio

data class ServicesUIStates(
    val usuario: Long? = null,
    val listaTickets:List<TicketVista?> = emptyList(),
    val isLoading:Boolean = false,
    val listaClientes: List<Personastodas.ClienteUI?> = emptyList(),
    val listaDispositivos: List<Dispositivo?> = emptyList(),
    val listaServicios: List<servicio?> = emptyList(),
)

data class TicketVista(
   var numeroFactura: Long? =0,
   var iDservicio:Long? = 0,
   var subtotal:Double? = 0.0,
   var Estado :Boolean? =false
       )