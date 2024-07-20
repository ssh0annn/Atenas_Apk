package com.solidtype.atenas_apk_2.gestion_tickets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.casos_tickets.CasosTicket
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TicketViewModel @Inject constructor(private val casos:CasosTicket):ViewModel() {
    var uiStates:MutableStateFlow<TicketUiStates> = MutableStateFlow(TicketUiStates())
    private var switch:MutableStateFlow<Boolean> = MutableStateFlow(uiStates.value.switch)
    init {
        getTickets()
    }

    fun onEvent(event:TicketEvents){
        when(event){
            is TicketEvents.Close -> closeTicket(event.tick)
            TicketEvents.Getickets -> getTickets()
            is TicketEvents.PaymentInfo -> getPaymentInfo(event.tick)
            is TicketEvents.Search -> searchTickets(event.any)
            TicketEvents.Switch -> switch.value = !uiStates.value.switch
            is TicketEvents.UpdatePago -> updatePayment(event.pago)
        }
    }

    private fun updatePayment(pago: tipo_venta) {
        viewModelScope.launch {
            casos.completarPago(pago)
            uiStates.update { it.copy(razones = "Pago actualizado!") }
        }
    }

    private fun searchTickets(any: String) {
        viewModelScope.launch {
            casos.buscarTickets(any, !switch.value).collect{ tickets ->
            uiStates.update { it.copy(tickets = tickets.map { data ->
                data.ticket }) }
            }
        }
    }
    private fun getPaymentInfo(tick: ticket) {
        viewModelScope.launch {
            uiStates.update { it.copy(infoPago = casos.getPaymentInfo(tick)) }

        }

    }

    private fun getTickets() {
        viewModelScope.launch {
            casos.getTickets(!switch.value).collect{ data ->
                uiStates.update { it.copy(tickets = data.map { it.ticket }) }
            }
        }
    }

    private fun closeTicket(tick: ticket){
        viewModelScope.launch {
            casos.closeTicket(tick)
        }

    }

}