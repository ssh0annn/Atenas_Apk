package com.solidtype.atenas_apk_2.facturacion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.FacturacionCasosdeUso
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.kk
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
class FacturaViewModel @Inject constructor(
    private val facturacionCasosdeUso: FacturacionCasosdeUso,
    private val prueba: kk
) : ViewModel() {
    var uiState = MutableStateFlow(FacturaUI())
        private set
    var job: Job? = null

    init {

        mostrarFactura()
    }
    fun mostrarFactura() {
        job?.cancel()
        job = viewModelScope.launch {
            uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            withContext(Dispatchers.IO) {
                val mostrar = facturacionCasosdeUso.mostrarTodo()

                mostrar.collect { product ->
                    uiState.update {
                        it.copy(
                            facturas = product, isLoading = false
                        )
                    }
                    println("Facturas $product")
                }
            }
        }
    }
    fun buscarfacturas(
        fechaini: LocalDate,
        fechafinal: LocalDate,
        datoSemejante: String,
    ) {
        if (datoSemejante.isBlank()) {
            uiState.update {
                it.copy(
                    error = "Campos vacios"
                )
            }
        } else {
            uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            job?.cancel()
            job = viewModelScope.launch {
                val buscarf =
                    facturacionCasosdeUso.buscarFacturas(fechaini, fechafinal, datoSemejante)

                buscarf.collect { product ->
                    uiState.update {
                        it.copy(buscar = product, isLoading = false)
                    }
                }
            }
        }
    }
    fun detealleFactura(
        NoFactura: Long,

        ) {
        viewModelScope.launch {
            uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            withContext(Dispatchers.IO) {
                val detalle = facturacionCasosdeUso.detallesFacturas(NoFactura)
                uiState.update { it.copy(detalles = detalle, isLoading = false) }
                println("Facturas $detalle")
            }

        }
    }
}
