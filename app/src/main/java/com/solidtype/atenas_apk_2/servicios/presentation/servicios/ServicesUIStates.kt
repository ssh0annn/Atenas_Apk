package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.servicios.modelo.servicio

data class ServicesUIStates(
    val usuario: usuario? = null,
    val listaTickets:List<TicketVista?> = emptyList(),
    val isLoading:Boolean = false,
    val listaClientes: List<Personastodas.ClienteUI?> = emptyList(),
    val listaDispositivos: List<Dispositivo?> = emptyList(),
    val listaServicios: List<servicio> = emptyList(),
    val impuestos: Boolean = false,
    val abono: Double = 0.0,
    val subtotal: Double = 0.0,
    val total: Double = 0.0
)

data class TicketVista(
   var numeroFactura: Long? =0,
   var iDservicio: String ="",
   var dispositivo: String = "",
   var subtotal:Double = 0.0,
   var total :Double = 0.0,
   var Estado :Boolean? =false
       )