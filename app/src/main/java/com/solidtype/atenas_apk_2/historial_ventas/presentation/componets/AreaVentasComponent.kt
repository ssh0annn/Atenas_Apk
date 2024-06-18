package com.solidtype.atenas_apk_2.historial_ventas.presentation.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorialUIState

@Composable
fun AreaVentas(
    ventasTickerTitulo: MutableState<String>,
    selected: MutableState<String>,
    uiState: HistorialUIState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = ventasTickerTitulo.value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = when (selected.value) {
                "Ventas" -> "${uiState.total} RD$"
                "Ticket" -> "${uiState.total2} RD$"
                else -> "0.0 RD$"
            },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
    }
}