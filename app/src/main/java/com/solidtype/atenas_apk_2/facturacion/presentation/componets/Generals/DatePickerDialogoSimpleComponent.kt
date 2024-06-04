package com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.ui.Components.DatePickerDialogo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogoSimple(
    showDatePicker: MutableState<Boolean>,
    datePickerState: DatePickerState,
    fechaIni: MutableState<String>
) {
    DatePickerDialogo(
        showDatePicker = showDatePicker.value,
        datePickerState = datePickerState,
        onDismissRequest = {
            showDatePicker.value = false
        },
        onClick = {
            showDatePicker.value = false
            fechaIni.value = datePickerState.selectedDateMillis.formatearFecha()
        }
    )
}