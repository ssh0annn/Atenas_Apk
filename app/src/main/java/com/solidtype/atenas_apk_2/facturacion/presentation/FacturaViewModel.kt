package com.solidtype.atenas_apk_2.facturacion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.facturacion.domain.casosUsos.FacturacionCasosdeUso
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.FacturaConDetalle
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
class  FacturaViewModel @Inject constructor(
    private val facturacionCasosdeUso: FacturacionCasosdeUso,
) : ViewModel() {
    var uiState = MutableStateFlow(FacturaUI())
        private set
    private var job: Job? = null

    init {
        onEvent(FacturasEvent.GetFacturas)
    }

    fun onEvent(event: FacturasEvent){
        when(event){
            is FacturasEvent.BuscarFacturas -> buscarfacturas(event.desde, event.hasta, event.semejante)
            FacturasEvent.GetFacturas -> mostrarFactura()
            FacturasEvent.LimpiarMensaje -> uiState.update { it.copy(mensaje = "") }
        }
    }
  private fun mostrarFactura() {
      viewModelScope.launch {
            uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            withContext(Dispatchers.IO) {
                facturacionCasosdeUso.mostrarTodo().collect { product ->
                    val factura:List<FacturaConDetalle> = product.map {
                        FacturaConDetalle(
                        factura = it.venta,
                        detalle = it.detalleVenta
                    ) }
                    uiState.update {
                        it.copy(facturaConDetalle = factura
                            ,isLoading = false)
                    }
                }
            }
        }
    }
    private fun buscarfacturas(
        fechaini: LocalDate,
        fechafinal: LocalDate,
        datoSemejante: String,
    ) {
        if (datoSemejante.isBlank()) {
            uiState.update {
                it.copy(
                    mensaje = "Campos vacios"
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
                withContext(Dispatchers.IO){
                    facturacionCasosdeUso.buscarFacturas(fechaini, fechafinal, datoSemejante).collect { product ->
                        val factura:List<FacturaConDetalle> = product.map { FacturaConDetalle(
                            factura = it.venta,
                            detalle = it.detalleVenta
                        ) }
                        uiState.update {
                            it.copy(facturaConDetalle = factura
                            , isLoading = false)
                        }
                    }
                }

            }
        }
    }

//    private fun Flow<List<venta?>>.helper(): Flow<List<FacturaConDetalle?>> {
//
//        return this.map { listaFacturas ->
//            listaFacturas.map { factura->
//                FacturaConDetalle(
//                    factura = factura,
//                    detalle = factura?.let { detalle -> facturacionCasosdeUso.detallesFacturas(detalle.id_venta) }
//                )
//            }
//        }
//    }
}
