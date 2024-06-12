package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_cliente.ClientesManage
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_dispositivos.DispositivosManger
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tickets.TicketsManeger
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_tipo_servicios.CasosTipoServicios
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_usuario.GetCurrentUserEmail
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.servicios.presentation.ticket.TicketEvents
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
    var ticket: MutableStateFlow<ServicioTicket> = MutableStateFlow(ServicioTicket())
        private set

    init {
        getCurrentUser()
//        getTickets()
//        getServicios()
//        getClientes()
//        getDispositivos()

        
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

    private fun crearTicket(ticket: ServicioTicket) {
        viewModelScope.launch {
            casosTicket.crearTicket(ticket)
        }
    }

    private fun crearServicios(servicios: servicio) {
        viewModelScope.launch {
            casosTiposServicios.crearTiposServicios(servicios)
        }
    }

    private fun getClientes() {
        viewModelScope.launch {
            casosCliente.getListaClientes().collect { clientes ->
                uiStates.update { it.copy(listaClientes = clientes) }
            }
        }
    }

    private fun getDispositivos() {
        viewModelScope.launch {
            casosDispositivo.getDispositivos().collect { dispositivos ->
                uiStates.update { it.copy(listaDispositivos = dispositivos) }
            }

        }
    }

    private fun getServicios() {
        viewModelScope.launch {
            casosTiposServicios.getTipoServicio().collect { servicios ->
                uiStates.update {
                    it.copy(listaServicios = servicios)
                }
            }
        }
    }

    private fun getTickets() {
        viewModelScope.launch {

            casosTicket.getTickets().map { listaTicket ->
                if (listaTicket.isNotEmpty()) {
                    listaTicket.map {
                        TicketVista(
                            numeroFactura = it.id_detalle_ticket,
                            iDservicio = it.tipo_servicio,
                            subtotal = it.total,
                            Estado = it.estado
                        )
                    }
                } else {
                    listaTicket.map {
                        TicketVista(
                            numeroFactura = 0,
                            iDservicio = 0,
                            subtotal = 0.0,
                            Estado = false
                        )
                    }
                }

            }.collect { listaVistaTicket ->
                uiStates.update {
                    it.copy(listaTickets = listaVistaTicket)
                }

            }
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            casoCurrentUser.getUser().collect{ lista ->
                uiStates.update { it.copy(usuario =lista.first()) }
                ticket.update { it.copy(vendedor =lista.first() ) }
            }
        }
    }

    /**
     * @param ServiceEvent
     * @return Gestiona y actualiza los eventos de servicios.
     * Esta funcion te permite Crear un nuevo servicio, capturar todos los servicios existente
     * tambien agrega nuevo el servicio al nuevo ticket.
     */

    fun onServiceEvent(event: ServiceEvent) {
        when (event) {
            is ServiceEvent.CreateServicio -> {
                crearServicios(event.servicio)
            }

            ServiceEvent.GetServicios -> {
                getServicios()
            }

            is ServiceEvent.ServicioSelecionado -> {
                ticket.update { it.copy(servicio = event.servicio) }
            }
        }
    }

    /**
     * @param VendedorEvent
     * Captura el usuario logeado.
     */
    fun onVendedor(event: VendedorEvent) {
        when (event) {
            VendedorEvent.GetCurrentUser -> {
                viewModelScope.launch {
                    val usuario = casoCurrentUser.getUser().collect{  usuarioss ->
                       usuarioss.forEach {  user ->
                           uiStates.update { it.copy(usuario = user) }
                           ticket.update { it.copy(vendedor = user) } }
                    }

                }
            }
        }
    }

    /**
     * @param ClientEvents
     * Maneja Eventos de cliente: Creacion, getAll y selecionar clientes.
     * Asi como agregar el cliente selecionado al nuevo ticket.
     */
    fun onCliente(event: ClientEvents) {
        when (event) {
            is ClientEvents.ClienteSelecionado -> {
                ticket.update { it.copy(cliente = event.clienteUI) }
            }
            is ClientEvents.CrearCliente -> {
                createCliente(event.clienteUI)
            }
            ClientEvents.GetClientes -> {
                getClientes()
            }
        }
    }

    fun onPayment(event: PagosEvent) {
        when (event) {
            is PagosEvent.DatosDelPago -> {
                ticket.update { it.copy(datosFinance = event.finaciero) }
            }
            is PagosEvent.TipoDePago -> {
                ticket.update { it.copy(tipoVenta = event.formaPagos)}
            }
        }
    }

    fun onDevice(event: DeviceEvent) {
        when (event) {
            is DeviceEvent.CrearDispositivo -> {
                createDispositivo(event.dispositivo)
            }
            is DeviceEvent.DispositivoSelecionado -> {
                ticket.update { it.copy(dispositivo = event.dispositivo)}
            }
            DeviceEvent.GetDispositivos -> {
                getDispositivos()
            }
        }
    }

    fun onTicket(event: OnTicket) {

        when (event) {
            is OnTicket.CrearTicket -> {
                println("Este es el ticket creado: ${event.ticket}")
                ticket.update { it.copy(cliente = null, dispositivo = null,
                    tipoVenta = null, servicio = null, detalles = null, datosFinance = null) }
            }
            OnTicket.GetTickets -> {
                getTickets()
            }

            is OnTicket.InforTicket -> {
                ticket.update { it.copy(detalles = event.infoTicket) }
            }
        }
    }

}