package com.solidtype.atenas_apk_2.servicios.presentation.ticket

import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket

data class TicketUIState(
    val tickets:List<ticket> = emptyList(),
    val isLoading:Boolean = false,

)