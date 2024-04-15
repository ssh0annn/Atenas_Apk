package com.solidtype.atenas_apk_2.historial_ventas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.historial_ventas.data.implementaciones.HistorialRepositoryImp
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.CasosHistorialReportes
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorialUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HistorailViewModel @Inject constructor(
    private val casosHistorialReportes: CasosHistorialReportes,
) : ViewModel() {


    var uiState = MutableStateFlow(HistorialUIState())
        private set


    init {
            MostrarHistoriar()
        }


    fun Exportar() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                println("inicia viewScope en la funcion que exporta en viewmodel")
                uiState.update { it.copy(isLoading = true) }
                println("withContext la funcion que exporta en viewmodel")
                val corr = casosHistorialReportes.exportarVentas(uiState.value.Historial)

                println("Se guardo el archivo en: ${corr}")

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


    fun buscarProductosventa(
        fecha_inicio: String,
        fecha_final: String,
        categoria: String
    ) {
        uiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            var total = 0.0
            val productosRangoventa =
                casosHistorialReportes.buscarporFechCatego(fecha_inicio, fecha_final, categoria)
            productosRangoventa.collect {
                product ->
                uiState.update {
                    it.copy(Historial = product, isLoading = false)
                }
                for (i in product){
                    total += i.Precio * i.Cantidad
                }
            }
            uiState.update {
                it.copy(total = total)
            }
           println( uiState.value.total)
        }
    }


    fun buscarProductosTicket(
        fechaIni: String ,
        fechaFinal: String ,
        catego: String

    ) {
        uiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            val productosRangoticket =
                casosHistorialReportes.verTicketsPorFechas(fechaIni, fechaFinal, catego)
            productosRangoticket.collect { product ->
                uiState.update {
                    it.copy(Ticket = product, isLoading = false)
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
                for ( i in product){
                    total += i.Precio.toDouble() * i.Cantidad.toInt()
                }
                uiState.update {
                    it.copy(Historial = product, isLoading = false, total = total)
                }

            }
        }
    }
}


