package com.solidtype.atenas_apk_2.servicios.presentation.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CasosTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tickets.TicketsManeger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(private val casosTicket: TicketsManeger) : ViewModel() {

    var uiState: MutableStateFlow<TicketUIState> = MutableStateFlow(TicketUIState())
        private set

    private val switch:Boolean = uiState.value.switch

    var job: Job? = null

    init {
        getTickets()
    }

    fun onEvent(events: TicketEvents){
        when(events){
            is TicketEvents.BuscarTicket -> {
                buscarTickets(events.semejante)
            }
            TicketEvents.GetTickets -> {
                getTickets()
            }

            TicketEvents.Switch -> uiState.update { it.copy(switch = !switch) }
            is TicketEvents.Completarpago -> completarPago(events.pago)
            is TicketEvents.GetPagoInfo -> getPagoInfo(events.tick)
        }
    }

    private fun getPagoInfo(tick: ticket) {
        viewModelScope.launch {
            uiState.update { it.copy(infopago = casosTicket.getInfoPago(tick)) }
        }
    }

    private fun completarPago(pago: tipo_venta) {
        viewModelScope.launch {  casosTicket.completarPago(pago)
        uiState.update { it.copy(razones = "Pago completado") }}

    }

    private fun getTickets() {
        job?.cancel()
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                casosTicket.getDetalleTicket(!switch).collect { tickets ->
                    uiState.update { it.copy(tickets = tickets) }
                }
            }
        }
    }
    private fun buscarTickets(anny:String){

        job?.cancel()
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                casosTicket.buscarTickets(anny, !switch).collect { tickets ->
                    uiState.update { it.copy(tickets = tickets) }
                }
            }
        }

    }
}