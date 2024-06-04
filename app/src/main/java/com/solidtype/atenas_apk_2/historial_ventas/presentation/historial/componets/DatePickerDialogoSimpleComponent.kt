package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.formatoDDBB
import com.solidtype.atenas_apk_2.util.ui.Components.DatePickerDialogo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogoSimple(
    identificador: MutableState<String>,
    selected: MutableState<String>,
    showDatePicker: MutableState<Boolean>,
    datePickerState: DatePickerState,
    fechaIni: MutableState<String>,
    viewModel: HistorailViewModel,
    fechaFin: MutableState<String>,
    context: Context
) {
    DatePickerDialogo(
        showDatePicker = showDatePicker.value,
        datePickerState = datePickerState,
        onDismissRequest = {
            showDatePicker.value = false
        },
        onClick = {
            showDatePicker.value = false
            when (identificador.value) {
                "FechaIni" -> fechaIni.value = datePickerState.selectedDateMillis.formatearFecha()
                "FechaFin" -> fechaFin.value = datePickerState.selectedDateMillis.formatearFecha()
            }
            when (selected.value) {
                "Ventas" -> {
                    viewModel.buscarProductosVenta(
                        fechaInicio = fechaIni.value.formatoDDBB(),
                        fechaFinal = fechaFin.value.formatoDDBB(),
                    )
                }
                "Ticket" -> {
                    viewModel.buscarProductosTicket(
                        fechaIni = fechaIni.value.formatoDDBB(),
                        fechaFinal = fechaFin.value.formatoDDBB(),
                    )
                }
            }
            Toast.makeText(context, "No olvides selecionar las fechas.", Toast.LENGTH_SHORT)
                .show()
        }
    )
}