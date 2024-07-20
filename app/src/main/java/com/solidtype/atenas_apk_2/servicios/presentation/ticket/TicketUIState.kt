package com.solidtype.atenas_apk_2.servicios.presentation.ticket

import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.TicketwithRelation
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket

data class TicketUIState(
    val tickets:List<TicketwithRelation> = emptyList(),
    val infopago:tipo_venta? = null,
    val isLoading:Boolean = false,
    val razones:String? = null,
    val switch:Boolean = true

)