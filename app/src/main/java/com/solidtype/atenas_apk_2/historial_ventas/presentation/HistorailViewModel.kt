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
        fecha_inicio: String = "02/02/24",
        fecha_final: String = "05/05/24",
        categoria: String = "venta"
    ) {
        viewModelScope.launch {
            val productosRangoventa =
                casosHistorialReportes.buscarporFechCatego(fecha_inicio, fecha_final, categoria)
            productosRangoventa.collect {
                product ->
                uiState.update {
                    it.copy(Historial = product)
                }
                for (i in product) {
                    println("nombre" + i.Nombre)
                    println(i.NombreCliente)
                    println(i.Cantidad)
                    println(i.Codigo)
                    println(i.Categoria)
                    println(i.Imei)
                    println(i.Marca)
                    println(i.Modelo)
                    println(i.Descripcion)
                    println(i.FechaIni)
                    println(i.FechaFin)
                }

            }
        }
    }


    fun buscarProductosTicket(
        fechaIni: String = "02/02/24",
        fechaFinal: String = "05/05/24",
        catego: String = "Ticket"

    ) {
        viewModelScope.launch {
            val productosRangoticket =
                casosHistorialReportes.verTicketsPorFechas(fechaIni, fechaFinal, catego)
            productosRangoticket.collect { product ->
                uiState.update {
                    it.copy(Ticket = product)
                }
                for (i in product) {
                    println("nombre" + i.Codigo)
                    println(i.NombreCliente)
                    println(i.Abono)
                    println(i.Marca)
                    println(i.Precio)
                    println(i.Servicio)
                    println(i.Modelo)
                    println(i.Telefono)
                }

            }
        }
    }


    fun MostrarHistoriar() {
        val mostrarHistory = casosHistorialReportes.mostrarVentas()

        viewModelScope.launch {
            mostrarHistory.collect { product ->
                uiState.update {
                    it.copy(Historial = product)
                }
                for (i in product) {
                    println("-----------------")
                    println("nombre" + i.Nombre)
                    println(i.NombreCliente)
                    println(i.Cantidad)
                    println(i.Codigo)
                    println(i.Categoria)
                    println(i.Imei)
                    println(i.Marca)
                    println(i.Modelo)
                    println(i.Descripcion)
                    println(i.FechaIni)
                    println(i.FechaFin)
                }
            }
        }
    }

}


