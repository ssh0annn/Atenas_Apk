package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogo(
    showDatePicker: Boolean,
    datePickerState: DatePickerState,
    onDismissRequest: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    if (showDatePicker) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            DatePickerDialog(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .scrollable(
                    state = rememberScrollState(), orientation = Orientation.Vertical
                ), onDismissRequest = onDismissRequest, confirmButton = {
                Button(
                    onClick = onClick
                ) {
                    Text("Aceptar")
                }
            }) {
                DatePicker(state = datePickerState)
            }
        }
    }
}