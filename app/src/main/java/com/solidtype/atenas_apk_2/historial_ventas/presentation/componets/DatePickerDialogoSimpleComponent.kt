package com.solidtype.atenas_apk_2.historial_ventas.presentation.componets

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorialEvent
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.formatoDDBB
import com.solidtype.atenas_apk_2.util.toLocalDate
import com.solidtype.atenas_apk_2.util.ui.components.DatePickerDialogo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogoSimple(
    identificador: MutableState<String>,
    selected: MutableState<String>,
    showDatePicker: MutableState<Boolean>,
    datePickerState: DatePickerState,
    fechaIni: MutableState<String>,
    viewModel: HistorailViewModel,
    fechaFin: MutableState<String>
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
                    viewModel.onEvent(
                        HistorialEvent.GetVentasFechas(
                            desde = fechaIni.value.formatoDDBB().toLocalDate(),
                            hasta = fechaFin.value.formatoDDBB().toLocalDate(),
                        )
                    )
                }
                "Ticket" -> {
                    viewModel.onEvent(
                        HistorialEvent.GetTicketsFechas(
                            desde = fechaIni.value.formatoDDBB().toLocalDate(),
                            hasta = fechaFin.value.formatoDDBB().toLocalDate()
                        )
                    )
                }
            }
        }
    )
}