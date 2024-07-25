package com.solidtype.atenas_apk_2.historial_ventas.presentation.componets

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.solidtype.atenas_apk_2.util.ui.components.DatePickerDialogo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogoSimple(
    showDatePicker: MutableState<Boolean>,
    datePickerState: DatePickerState,
    onClick: () -> Unit,
) {
    DatePickerDialogo(
        showDatePicker = showDatePicker.value,
        datePickerState = datePickerState,
        onDismissRequest = {
            showDatePicker.value = false
        },
        onClick = onClick
    )
}