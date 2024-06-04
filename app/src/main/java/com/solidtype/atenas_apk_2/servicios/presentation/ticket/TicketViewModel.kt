package com.solidtype.atenas_apk_2.servicios.presentation.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        }
    }
    private fun getTickets() {
        job?.cancel()
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                casosTicket.getDetalleTicket().collect { tickets ->
                    uiState.update { it.copy(tickets = tickets) }
                }
            }
        }
    }
    private fun buscarTickets(anny:String){

        job?.cancel()
        job = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                casosTicket.buscarTickets(anny).collect { tickets ->
                    uiState.update { it.copy(tickets = tickets) }
                }
            }
        }

    }
}