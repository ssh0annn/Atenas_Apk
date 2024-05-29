package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel

@Composable
fun SelectorVentaTicket(
    selected: MutableState<String>,
    ventasTickerTitulo: MutableState<String>,
    viewModel: HistorailViewModel,
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
                    viewModel.MostrarHistoriar()//Se debe lanzar un evento
                }
                "Ticket" -> {
                    ventasTickerTitulo.value = "Cuenta x Cobrar"
                    viewModel.mostrarTicket()// se debe lanzar un evento de consulta
                }
            }
        }
    }
}