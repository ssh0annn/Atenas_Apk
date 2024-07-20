package com.solidtype.atenas_apk_2.historial_ventas.presentation


import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.mediator.MediatorHistorialVentasImpl
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.CasosHistorialReportes
//import com.solidtype.atenas_apk_2.util.toMapa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class HistorailViewModel @Inject constructor(
    private val casosHistorialReportes: CasosHistorialReportes

) : ViewModel() {

    var uiState = MutableStateFlow(HistorialUIState())
        private set

    private var job : Job? = null

    init {

         onEvent(HistorialEvent.GetTodosTodasVentas)
         onEvent(HistorialEvent.GetTodosTickets)
    }

    fun onEvent(event: HistorialEvent){
        when(event){
            HistorialEvent.Exportar -> exportar()
            is HistorialEvent.GetTicketsFechas -> buscarProductosTicket(event.desde, event.hasta)
            HistorialEvent.GetTodosTickets -> mostrarTicket()
            HistorialEvent.GetTodosTodasVentas -> mostrarHistoriar()
            is HistorialEvent.GetVentasFechas -> buscarProductosVenta(event.desde, event.hasta)
        }
    }

    private fun exportar() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                uiState.update { it.copy(isLoading = true) }
                val corr: Uri = if (uiState.value.ventasOTicket) {
                    casosHistorialReportes.exportarTickets(uiState.value.Ticket)
                } else {
                    casosHistorialReportes.exportarVentas(uiState.value.Historial)
                }
                    uiState.update {
                        it.copy(uriPath = corr,isLoading = false)
                    }
            }
        }
    }


    private  fun buscarProductosVenta(
        desde: LocalDate, hasta: LocalDate
    ) {
        job?.cancel()
        uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            job = viewModelScope.launch {

                casosHistorialReportes.buscarporFechCatego(desde, hasta)
                    .collect { tipoVenta ->
                        uiState.update { state ->
                            state.copy(
                                total = tipoVenta.sumOf { it.tipoVenta.total },
                                abono = tipoVenta.sumOf { it.tipoVenta.abono },
                                impuesto = tipoVenta.sumOf { it.tipoVenta.impuesto } ,
                                subtotal =  tipoVenta.sumOf { it.tipoVenta.subtotal },
                                Historial = tipoVenta,
                                isLoading = false
                            )
                        }


                    }
            }

    }

    private  fun buscarProductosTicket(
        desde: LocalDate,
        hasta: LocalDate,
    ) {
        job?.cancel()

        uiState.update {
            it.copy(
                isLoading = true
            )
        }
        job = viewModelScope.launch {

            casosHistorialReportes.verTicketsPorFechas(desde, hasta)
                .collect { ventaTicket ->
                    uiState.update { state ->
                        state.copy(
                            total = ventaTicket.sumOf { it.tipoVenta.total },
                            abono = ventaTicket.sumOf { it.tipoVenta.abono },
                            impuesto = ventaTicket.sumOf { it.tipoVenta.impuesto },
                            subtotal = ventaTicket.sumOf { it.tipoVenta.subtotal },
                            restante = ventaTicket.sumOf { it.tipoVenta.restantante },
                            Ticket = ventaTicket
                        )
                    }

                }

        }

    }
    private fun mostrarHistoriar() {
        job?.cancel()
        uiState.update {
            it.copy(
                isLoading = true
            )
        }
        job = viewModelScope.launch {
            casosHistorialReportes.mostrarVentas().collect { tipoVenta ->
                uiState.update { state ->
                    state.copy(
                        total = tipoVenta.sumOf { it.tipoVenta.total },
                        abono = tipoVenta.sumOf { it.tipoVenta.abono },
                        impuesto = tipoVenta.sumOf { it.tipoVenta.impuesto },
                        subtotal = tipoVenta.sumOf { it.tipoVenta.subtotal },
                        Historial = tipoVenta,
                        isLoading = false,
                        ventasOTicket = false
                    )
                }
            }
        }
    }

    private  fun mostrarTicket() {
        job?.cancel()
        job = viewModelScope.launch {
            casosHistorialReportes.verTodosTickets().collect { ventaTicket ->
                uiState.update { state ->
                    state.copy(
                        total = ventaTicket.sumOf { it.tipoVenta.total },
                        abono = ventaTicket.sumOf { it.tipoVenta.abono },
                        impuesto = ventaTicket.sumOf { it.tipoVenta.impuesto },
                        subtotal = ventaTicket.sumOf { it.tipoVenta.subtotal },
                        restante = ventaTicket.sumOf { it.tipoVenta.restantante },
                        ventasOTicket = true,
                        Ticket = ventaTicket,
                        isLoading = false

                    )
                }
            }
        }
    }
}








