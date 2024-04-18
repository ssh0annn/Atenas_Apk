package com.solidtype.atenas_apk_2.historial_ventas.presentation

import android.widget.Toast
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HistorailViewModel @Inject constructor(
    private val casosHistorialReportes: CasosHistorialReportes,
) : ViewModel() {


    var uiState = MutableStateFlow(HistorialUIState())



    init {
            MostrarHistoriar()

        println("total1 =" + uiState.value.total)

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
        if (fecha_inicio.isBlank() || fecha_final.isBlank() || categoria.isBlank()){
            uiState.update {
                it.copy(
                    error = "Campos vacio"
                )
            }
        }else{
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
    }


    fun buscarProductosTicket(
        fechaIni: String ,
        fechaFinal: String ,
        catego: String,
    ) {
        if (fechaIni.isBlank() || fechaFinal.isBlank() || catego.isBlank()){
            uiState.update {
                it.copy(
                    error = "Campo Vacios"
                )
            }
        }else{
            uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            viewModelScope.launch {
                val productosRangoticket = casosHistorialReportes.verTicketsPorFechas(fechaIni, fechaFinal, catego)
                var deuda = 0.0
                productosRangoticket.collect { product ->
                    for (i in product){
                        deuda += i.Precio - i.Abono
                    }
                    uiState.update {
                        it.copy(Ticket = product, isLoading = false, total2 = deuda)
                    }
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
                    println(i)
                }
                println("total" +  total)


                uiState.update {
                  it.copy(Historial = product, isLoading = false, total = total)
              }
            }
        }
    }

    fun mostrarTicket() {
        val mostrarTick = casosHistorialReportes.verTodosTickets()
        var deuda = 0.0
        viewModelScope.launch {
            mostrarTick.collect { product ->
                for (i in product) {
                    deuda += i.Restante
                }
                uiState.update {
                    it.copy(Ticket = product, isLoading = false, total2 = deuda)
                }
            }
            uiState.update {
                it.copy(isLoading = false)
            }
        }
    }


}






