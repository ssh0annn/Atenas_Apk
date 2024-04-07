package com.solidtype.atenas_apk_2.historial_ventas.domain.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.historial_ventas.domain.casosusos.CasosHistorialReportes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistorailViewModel @Inject constructor(
    private val casosHistorialReportes: CasosHistorialReportes,
): ViewModel() {


    var uiState = MutableStateFlow(HistorialUIState())
        private set


    init {
        MostrarHistoriar()
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

    fun MostrarHistoriar() {
        val mostrarHistory = casosHistorialReportes.mostrarVentas()

        viewModelScope.launch {
            mostrarHistory.collect {
                product ->
                uiState.update {
                    it.copy(Historial = product)
                }
               println(product) // Accedemos a la propiedad Nombre de product, no de mostrarHistory
            }
        }

    }



}


