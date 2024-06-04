package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_cliente.ClientesManage
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_dispositivos.DispositivosManger
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tickets.TicketsManeger
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.CasosTipoServicios
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_usuario.GetCurrentUserEmail
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiciosViewModel @Inject constructor(
    private val casosCliente: ClientesManage,
    private val casosDispositivo: DispositivosManger,
    private val casosTicket: TicketsManeger,
    private val casosTiposServicios: CasosTipoServicios,
    private val casoCurrentUser: GetCurrentUserEmail

) : ViewModel() {

    var uiStates: MutableStateFlow<ServicesUIStates> = MutableStateFlow(ServicesUIStates())
        private set
    var job: Job? = null

    init {

        getTickets()
    }

    private fun createCliente(cliente: Personastodas.ClienteUI) {
        viewModelScope.launch {
            casosCliente.agregarNuevoCliente(cliente)
        }
    }

    private fun createDispositivo(dispositivo: Dispositivo) {
        viewModelScope.launch {

            casosDispositivo.crearDispositivo(dispositivo)
        }
    }

    private fun crearTicket(ticket: ticket) {
        viewModelScope.launch {
            casosTicket.crearTicket(ticket)
        }
    }

    private fun crearServicios(servicios: servicio) {
        viewModelScope.launch {
            casosTiposServicios.crearTiposServicios(servicios)
        }
    }
    private fun getClientes(){
        viewModelScope.launch {
            casosCliente.getListaClientes().collect{ clientes ->
                uiStates.update { it.copy(listaClientes = clientes) }
            }
        }
    }
    private fun getDispositivos(){
        viewModelScope.launch {
            casosDispositivo.getDispositivos().collect{ dispositivos ->
                uiStates.update { it.copy(listaDispositivos = dispositivos) }
            }

        }
    }
    private fun getServicios(){
        viewModelScope.launch {
            casosTiposServicios.getTipoServicio().collect{ servicios ->
                uiStates.update{
                    it.copy(listaServicios = servicios)
                }
            }
        }
    }
    private fun getTickets(){
        viewModelScope.launch {
          casosTicket.getTickets().map { listaTicket ->
               if(listaTicket.isNotEmpty()){
                   listaTicket.map {
                       TicketVista(
                           numeroFactura =  it.id_detalle_ticket,
                           iDservicio = it.tipo_servicio,
                           subtotal= it.total,
                           Estado= it.estado
                       )
                   }
               }else{
                   listaTicket.map {
                       TicketVista(
                           numeroFactura = 0,
                           iDservicio = 0,
                           subtotal = 0.0,
                           Estado= false
                       )
                   }
               }

           }.collect{ listaVistaTicket ->
               uiStates.update {
                   it.copy(listaTickets =listaVistaTicket )
               }

           }

        }
    }
    private fun getCurrentUser(){
        viewModelScope.launch {
            uiStates.update { it.copy(usuario =  casoCurrentUser.getUser() ) }
        }
    }

    fun onEvent(event: ServiceEvent) {
        when (event) {
            is ServiceEvent.CrearCliente -> {
                createCliente(event.clienteUI)
            }

            is ServiceEvent.CrearDispositivo -> {
                createDispositivo(event.dispositivo)
            }

            is ServiceEvent.CrearTicket -> {
                crearTicket(event.ticket)
            }

            is ServiceEvent.CreateServicio -> {
                crearServicios(event.servicio)
            }

            ServiceEvent.GetClientes -> {
                getClientes()
            }

            ServiceEvent.GetDispositivos -> {
                getDispositivos()
            }

            ServiceEvent.GetServicios -> {
                getServicios()
            }

            ServiceEvent.GetTickets -> {
                getTickets()
            }

            ServiceEvent.GetCurrentUser -> {
                getCurrentUser()
            }
        }

    }

}