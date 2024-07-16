package com.solidtype.atenas_apk_2.historial_ventas.presentation.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.util.ui.components.SelecionarFecha
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inputs(
    identificador: MutableState<String>,
    datePickerState1: DatePickerState,
    fechaIni: MutableState<String>,
    showDatePicker1: MutableState<Boolean>,
    datePickerState2: DatePickerState,
    fechaFin: MutableState<String>,
    showDatePicker2: MutableState<Boolean>,
    selected: MutableState<String>,
    ventasTickerTitulo: MutableState<String>,
    viewModel: HistorailViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SelecionarFecha(
            "Fecha Inicial",
            datePickerState1.selectedDateMillis,
            fechaIni.value
        ) {
            showDatePicker1.value = true
            identificador.value = "FechaIni"
        }
        Spacer(modifier = Modifier.width(16.dp))
        SelecionarFecha(
            "Fecha Final",
            datePickerState2.selectedDateMillis,
            fechaFin.value
        ) {
            showDatePicker2.value = true
            identificador.value = "FechaFin"
        }
        Spacer(modifier = Modifier.width(200.dp))
        SelectorVentaTicket(selected, ventasTickerTitulo, viewModel, fechaIni, fechaFin)
    }
}