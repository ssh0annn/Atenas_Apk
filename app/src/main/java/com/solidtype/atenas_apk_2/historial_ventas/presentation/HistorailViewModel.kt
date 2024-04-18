package com.solidtype.atenas_apk_2.historial_ventas.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.historial_ventas.data.implementaciones.HistorialRepositoryImp
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteHistoVentaFB.mediator.MediatorHistorialVentas
import com.solidtype.atenas_apk_2.historial_ventas.data.remoteTicketsFB.mediadorTicket.RemoteTicketsFB
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.CasosHistorialReportes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HistorailViewModel @Inject constructor(
    private val casosHistorialReportes: CasosHistorialReportes

) : ViewModel() {


    var uiState = MutableStateFlow(HistorialUIState())


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                casosHistorialReportes.syncronizacion()
            }
        }

        MostrarHistoriar()
    //  mostrarTicket()

    }


    fun Exportar() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                println("inicia viewScope en la funcion que exporta en viewmodel")
                uiState.update { it.copy(isLoading = true) }
                println("withContext la funcion que exporta en viewmodel")
                val corr:Uri
                if(uiState.value.ventasOTicket){
                    corr =  casosHistorialReportes.exportarTickets(uiState.value.Ticket)
                    println("Se guardo el archivo en: ${corr} son tickets")

                }else{
                     corr = casosHistorialReportes.exportarVentas(uiState.value.Historial)
                    println("Se guardo el archivo en: ${corr} Es ventas")

                }



                uiState.update { it.copy(isLoading = false) }
                println("Salgo del withcontext la funcion que exporta en viewmodel")

                withContext(Dispatchers.Main) {
                    uiState.update {
                        it.copy(uriPath = corr.path.toString())
                    }
                }
            }
        }
    }


    fun buscarProductosVenta(
        fecha_inicio: String,
        fecha_final: String,
        categoria: String
    ) {

        viewModelScope.launch {
            var total = 0.0
            val productosRangoventa =
                casosHistorialReportes.buscarporFechCatego(fecha_inicio,
                    fecha_final, "venta")
            productosRangoventa.collect { product ->
                uiState.update {
                    it.copy(Historial = product, isLoading = false)
                }
                println("Resultados de la busqueda: $product")
                for (i in product) {
                    total += i.Precio * i.Cantidad
                }
            }
            uiState.update {
                it.copy(total = total)
            }

        }
    }

    fun buscarProductosTicket(
        fechaIni: String,
        fechaFinal: String,
        catego: String = "ticket"

    ) {

        viewModelScope.launch {
            val productosRangoticket =
                casosHistorialReportes.verTicketsPorFechas(fechaIni, fechaFinal, catego)
            var deuda = 0.0
            productosRangoticket.collect { product ->
                for (i in product) {
                    deuda += i.Precio - i.Abono
                }
                uiState.update {
                    it.copy(Ticket = product, isLoading = false, total2 = deuda)
                }
            }
        }
    }


    fun MostrarHistoriar() {
        val mostrarHistory = casosHistorialReportes.mostrarVentas()
        var total = 0.0
        uiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {

            mostrarHistory.collect { product ->
                for (i in product) {
                    total += i.Precio.toDouble() * i.Cantidad.toInt()
                }
                uiState.update {
                    it.copy(Historial = product, isLoading = false, total = total, ventasOTicket = false)
                }

            }
            casosHistorialReportes.syncronizacion()


        }
    }

    fun mostrarTicket() {
        val mostrarTick = casosHistorialReportes.verTodosTickets()

        var deuda = 0.0
        viewModelScope.launch {
            mostrarTick.collect { product ->
                uiState.update {
                    it.copy(Ticket = product, isLoading = false, total2 = deuda, ventasOTicket = true)
                }
                for (i in product) {
                    deuda += i.Precio - i.Abono
                }
            }
            uiState.update {
                it.copy(isLoading = false)
            }
        }
    }


}






