package com.solidtype.atenas_apk_2.historial_ventas.presentation


import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.mediator.MediatorHistorialVentasImpl
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.CasosHistorialReportes
import com.solidtype.atenas_apk_2.util.toIsoDate
import com.solidtype.atenas_apk_2.util.toLocalDate
//import com.solidtype.atenas_apk_2.util.toMapa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HistorailViewModel @Inject constructor(
    private val casosHistorialReportes: CasosHistorialReportes,
    private val classesAsyncs: MediatorHistorialVentasImpl

) : ViewModel() {

    var uiState = MutableStateFlow(HistorialUIState())
        private set

    private var job : Job? = null


    init {
        MostrarHistoriar()
        //  mostrarTicket()
    }


    fun Exportar() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                uiState.update { it.copy(isLoading = true) }
//                val corr: Uri = if (uiState.value.ventasOTicket) {
//                    casosHistorialReportes.exportarTickets(uiState.value.Ticket)
//
//                } else {
//                    casosHistorialReportes.exportarVentas(uiState.value.Historial)
//                }
                uiState.update { it.copy(isLoading = false) }

                withContext(Dispatchers.IO) {
//                    uiState.update {
//                        it.copy(uriPath = corr)
//                    }
                }
            }
        }
    }


    fun buscarProductosVenta(
        fechaInicio: String, fechaFinal: String
    ) {
        job?.cancel()
        if (fechaInicio.isBlank() || fechaFinal.isBlank()) {

            uiState.update {
                it.copy(
                    error = "Campos vacio"
                )
            }
            println("Campo vaio :fecha inicio $fechaInicio <- o fecha final $fechaFinal <-")
        } else {
            uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            println("Campo vaio :fecha inicio $fechaInicio <- o fecha final $fechaFinal <-")
            job = viewModelScope.launch {
                var total = 0.0
                casosHistorialReportes.buscarporFechCatego(fechaInicio.toIsoDate().toLocalDate(), fechaFinal.toIsoDate().toLocalDate())
                    .collect { product ->
                        product.sumOf { it.tipoVenta.total }
                        product.sumOf { it.tipoVenta.abono }
                        product.sumOf { it.tipoVenta.impuesto }
                        product.sumOf { it.tipoVenta.subtotal }
                        println("Qui lo que se pidio : $product")
//                        uiState.update {
//                            it.copy(Historial = product, isLoading = false)
//                        }

                    }
                uiState.update {
                    it.copy(total = total)
                }
                println(uiState.value.total)
            }
        }
    }

    fun buscarProductosTicket(
        fechaIni: String,
        fechaFinal: String,
    ) {
        job?.cancel()
        if (fechaIni.isBlank() || fechaFinal.isBlank()) {
            uiState.update {
                it.copy(
                    error = "Campos Vacios"
                )
            }
        } else {
            uiState.update {
                it.copy(
                    isLoading = true
                )
            }
           job = viewModelScope.launch {
//                val productosRangoticket =
//                    casosHistorialReportes.verTicketsPorFechas(fechaIni, fechaFinal)
                var deuda  = 0.0
             //   productosRangoticket.collect { product ->
//                    for (i in product) {
//                        deuda += i.total
//                    }
//                    uiState.update {
//                        it.copy(Ticket = product, isLoading = false, total2 = deuda)
//                    }
            }
        }
    }


    fun MostrarHistoriar() {
        job?.cancel()
        val mostrarHistory = casosHistorialReportes.mostrarVentas()
        var total = 0.0
        uiState.update {
            it.copy(
                isLoading = true
            )
        }
        job= viewModelScope.launch {

            mostrarHistory.collect { product ->
                for (i in product) {
                   // total += i.total
                    println(i)
                }
//                uiState.update {
//                    it.copy(
//                        Historial = product, isLoading = false, total = total, ventasOTicket = false
//                    )
//                }
                println("total" + total)
            }
            casosHistorialReportes.syncronizacion()


        }
    }

    fun mostrarTicket() {
        job?.cancel()
        val mostrarTick = casosHistorialReportes.verTodosTickets()

        var deuda = 0.0
       job =  viewModelScope.launch {
            mostrarTick.collect { product ->
//                for (i in product) {
//                    deuda += i.total
//                }
//                uiState.update {
//                    it.copy(
//                        Ticket = product, isLoading = false, total2 = deuda, ventasOTicket = true
//                    )
//                }
            }
            uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}







