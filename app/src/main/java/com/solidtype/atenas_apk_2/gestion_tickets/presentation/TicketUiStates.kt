package com.solidtype.atenas_apk_2.gestion_tickets.presentation

import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket

data class TicketUiStates (
    val switch:Boolean = true,
    val tickets:List<ticket> = emptyList(),
    val infoPago:tipo_venta? = null,
    val isloading:Boolean = false,
    val razones:String? = null
)