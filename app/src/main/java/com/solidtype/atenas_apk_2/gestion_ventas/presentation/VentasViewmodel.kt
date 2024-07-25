package com.solidtype.atenas_apk_2.gestion_ventas.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_cliente.ClientesManage
import com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_usuario.GetCurrentUserEmail
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ClientEvents
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.PagosEvent
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.VendedorEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VentasViewmodel @Inject constructor(
    private val casoCurrentUser: GetCurrentUserEmail,
    @ApplicationContext private val context: Context,
    private val casosCliente: ClientesManage,
) : ViewModel() {

    
    private val CORREO: String = "correo"
    private val recuerdame = context.getSharedPreferences("recuerdame", Context.MODE_PRIVATE)


    private fun createCliente(cliente: Personastodas.ClienteUI) {
        viewModelScope.launch {
            casosCliente.agregarNuevoCliente(cliente)
        }
    }
    private fun getClientes() {
        viewModelScope.launch {
            casosCliente.getListaClientes().collect { clientes ->
               // uiStates.update { it.copy(listaClientes = clientes) }
            }
        }
    }
    private fun getCurrentUser() {
        viewModelScope.launch {
            casoCurrentUser.getUser(recuerdame.getString(CORREO, "").toString()).collect{ lista ->
                if(lista.isNotEmpty()){
//                    uiStates.update { it.copy(usuario =lista.first()) }
//                    ticket.update { it.copy(vendedor =lista.first() ) }
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
               // ticket.update { it.copy(cliente = event.clienteUI) }
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
                val datosRealeas = event.finaciero

                datosRealeas.id_tipo_venta = System.currentTimeMillis()//Para revision
//                datosRealeas.abono = uiStates.value.abono
//                if(uiStates.value.impuestos){
//                    datosRealeas.impuesto =  datosRealeas.subtotal * 0.18
//                    datosRealeas.total = datosRealeas.subtotal + datosRealeas.impuesto
//                    datosRealeas.restantante =datosRealeas.total - datosRealeas.abono
//
//                }else{
//                    datosRealeas.impuesto = 0.0
//                    datosRealeas.total = datosRealeas.subtotal + datosRealeas.impuesto
//                    datosRealeas.restantante = datosRealeas.total - datosRealeas.abono
//                }
//                ticket.update { it.copy(datosFinance = datosRealeas ) }
                println("TOdos los datos de pago $datosRealeas")
                println("REstante : ${datosRealeas.restantante}")
            }

            is PagosEvent.Impuestos -> {
//                uiStates.update { it.copy(impuestos = event.impuestos) }
//                ticket.value.datosFinance?.let {
//                    onPayment(PagosEvent.DatosDelPago(it))
//                }
            }

            is PagosEvent.Abono -> {
//                uiStates.update { it.copy(abono = event.abono) }
//                ticket.value.datosFinance?.let {
//                    onPayment(PagosEvent.DatosDelPago(it))
//                }
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
                    casoCurrentUser.getUser(recuerdame.getString(CORREO, "").toString())
                        .collect { usuarioss ->
                            usuarioss.forEach { user ->
//                                uiStates.update { it.copy(usuario = user) }
//                                ticket.update { it.copy(vendedor = user) }
                            }
                        }
                }
            }
        }
    }
}