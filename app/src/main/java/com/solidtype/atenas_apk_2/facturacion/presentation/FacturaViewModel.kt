package com.solidtype.atenas_apk_2.facturacion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.FacturacionCasosdeUso
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.FacturaConDetalle
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.kk
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class  FacturaViewModel @Inject constructor(
    private val facturacionCasosdeUso: FacturacionCasosdeUso,
    private val prueba: kk
) : ViewModel() {
    var uiState = MutableStateFlow(FacturaUI())
        private set
    private var job: Job? = null

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

                facturacionCasosdeUso.mostrarTodo().helper().collect { product ->
                    uiState.update {
                        it.copy(
                            facturaConDetalle = product, isLoading = false
                        )
                    }
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
                withContext(Dispatchers.IO) {
                    facturacionCasosdeUso.buscarFacturas(fechaini, fechafinal, datoSemejante)
                        .helper().collect { product ->
                        uiState.update {
                            it.copy(facturaConDetalle = product, isLoading = false)
                        }
                    }
                }
            }
        }
    }

    private fun Flow<List<venta?>>.helper(): Flow<List<FacturaConDetalle?>> {

        return this.map { ventas ->
            ventas.map {
                FacturaConDetalle(
                    factura = it,
                    detalle = it?.let { it1 -> facturacionCasosdeUso.detallesFacturas(it1.id_venta) }
                )
            }
        }
    }
}
