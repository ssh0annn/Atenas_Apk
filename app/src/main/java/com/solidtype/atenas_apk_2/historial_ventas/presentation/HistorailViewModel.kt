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
    private val historialRepositoryImp: HistorialRepositoryImp,
): ViewModel() {


    var uiState = MutableStateFlow(HistorialUIState())
        private set





    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                historialRepositoryImp.insertalTemporal(
                    HistorialVentaEntidad(
                        Codigo = 222,
                        Nombre = "juan",
                        NombreCliente =  "gomez",
                        Imei = "fsddfsdf",
                        Descripcion = "juan luis gerra",
                        Cantidad = 12,
                        Categoria = "venta",
                        Modelo = "jn ",
                        Marca = "luis",
                        Precio = 20.0,
                        TipoVenta = "juan luis gerra",
                        FechaFin = "02/02/2024" ,
                        FechaIni = "05/05/2024" ,
                    )
                )
            }
            MostrarHistoriar()
            buscarProductos()
        }
    }

 /*   fun MostrarHistoriar() {
        val mostrarHistory = casosHistorialReportes.mostrarVentas()

        viewModelScope.launch {
            mostrarHistory.collect { product ->
                uiState.update {
                    it.copy(Historial = product)
                }
                println(mostrarHistory.Nombre)
            }
        }
    }*/

    fun Exportar(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                println("inicia viewScope en la funcion que exporta en viewmodel")
                uiState.update { it.copy(isLoading = true) }
                println("withContext la funcion que exporta en viewmodel")
                val corr = casosHistorialReportes.exportarVentas(uiState.value.Historial)

                println("Se guardo el archivo en: ${corr}")

                uiState.update { it.copy(isLoading = false) }
                println("Salgo del withcontext la funcion que exporta en viewmodel")

                withContext(Dispatchers.Main){
                    uiState.update {
                        it.copy(uriPath = corr.path.toString())
                    }
                }


            }

        }

    }


    fun buscarProductos(fecha_inicio: String = "02/02/24", fecha_final: String ="05/05/24", categoria: String = "venta") {

            viewModelScope.launch {
                val productosRango = casosHistorialReportes.buscarporFechCatego(fecha_inicio, fecha_final, categoria)
                productosRango.collect{
                    product ->
                    uiState.update {
                    it.copy(Historial = product)
                }

                    for ( i in product){
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

    fun MostrarHistoriar() {
        val mostrarHistory = casosHistorialReportes.mostrarVentas()

        viewModelScope.launch {
            mostrarHistory.collect {
                product ->
                uiState.update {
                    it.copy(Historial = product)
                }

              /* println(product)

                for ( i in product){
                    println("nombre" + i.Nombre)
                    println(i.NombreCliente)
                    println(i.Cantidad)
                    println(i.Codigo)
                    println(i.Categoria)
                    println(i.Imei)
                    println(i.Marca)
                    println(i.Modelo)
                    println(i.Descripcion)
                }*/



            }
        }
    }



}


