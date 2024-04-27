package com.solidtype.atenas_apk_2.facturacion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.FacturacionCasosdeUso
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class FacturaViewModel  @Inject constructor(
    private val facturacionCasosdeUso: FacturacionCasosdeUso,
    ) : ViewModel(){

    var uiState = MutableStateFlow(FacturaUI())

    init {
        mostrarFactura()
    }

    fun mostrarFactura(){
        val mostrar = facturacionCasosdeUso.mostrarTodo()
        uiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            mostrar.collect { product ->
                uiState.update {
                    it.copy(
                            facturas = product, isLoading = false
                    )
                }
            }
        }
    }

    fun buscarfacturas(
        fechaini: LocalDate,
        fechafinal: LocalDate,
        datoSemejante: String,
    ){
        if(/*fechaini.isBlank() || fechafinal.isBlank() || */datoSemejante.isBlank()){
            uiState.update {
                it.copy(
                    error = "Campos vacios"
                )
            }
        }else{
            uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            viewModelScope.launch {
                val buscarf = facturacionCasosdeUso.buscarFacturas(fechaini, fechafinal, datoSemejante)

                buscarf.collect{ product ->
                    uiState.update {
                        it.copy( buscar = product, isLoading = false)
                    }
                }
            }
        }
    }


    fun detealleFactura(
        NoFactura:Long,

    ){
        val detalles = facturacionCasosdeUso.detallesFacturas(NoFactura)

        uiState.update{
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            detalles.collect{ product ->
                uiState.update {
                    it.copy( detalles = product, isLoading = false )
                }
            }
        }
    }


}