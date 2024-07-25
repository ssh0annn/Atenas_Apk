package com.solidtype.atenas_apk_2.historial_ventas.presentation.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorialEvent

@Composable
fun SelectorVentaTicket(
    selected: MutableState<String>,
    ventasTickerTitulo: MutableState<String>,
    viewModel: HistorailViewModel,
    fechaIni: MutableState<String>,
    fechaFin: MutableState<String>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.width(200.dp)
    ) {
        DropdownSelect(
            items = listOf("Ventas", "Ticket"),
            selectedItem = selected.value,
        ) {
            selected.value = it
            when (selected.value) {
                "Ventas" -> {
                    ventasTickerTitulo.value = "Ventas"
                    fechaIni.value = ""
                    fechaFin.value = ""
                    viewModel.onEvent(
                        HistorialEvent.GetTodosTodasVentas
                    )
                }
                "Ticket" -> {
                    ventasTickerTitulo.value = "Cuenta x Cobrar"
                    fechaIni.value = ""
                    fechaFin.value = ""
                    viewModel.onEvent(
                        HistorialEvent.GetTodosTickets
                    )
                }
            }
        }
    }
}