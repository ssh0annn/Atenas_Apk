package com.solidtype.atenas_apk_2.util.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro

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
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(
                    state = rememberScrollState(), orientation = Orientation.Vertical
                )
        ) {
            MyDatePickerDialog(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp),
                onDismissRequest = onDismissRequest,
                confirmButton = {
                    Boton(
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "cotejo",
                                modifier = Modifier
                                    .size(20.dp),
                                tint = Blanco
                            )
                        },
                        onClick = onClick
                    )
                },
                content = {
                    DatePicker(
                        state = datePickerState,
                        modifier = Modifier
                            .background(Blanco),
                        colors = DatePickerDefaults.colors(
                            containerColor = GrisClaro,
                            selectedYearContainerColor = AzulGris,
                            selectedDayContainerColor = AzulGris,
                            todayContentColor = AzulGris,
                            todayDateBorderColor = AzulGris
                        )
                    )
                }
            )
        }
    }
}