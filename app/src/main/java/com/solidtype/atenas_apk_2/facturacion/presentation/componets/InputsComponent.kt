package com.solidtype.atenas_apk_2.facturacion.presentation.componets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.util.ui.components.SelecionarFecha

@Composable
@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
fun Inputs(
    noFacturaCliente: MutableState<String>,
    datePickerState1: DatePickerState,
    fechaIni: String,
    showDatePicker1: MutableState<Boolean>,
    datePickerState2: DatePickerState,
    fechaFin: String,
    showDatePicker2: MutableState<Boolean>
) {
    Row {//Entradas
        InputBlanco(
            placeholder = "Buscar por Cliente o No. de Factura...",
            valor = noFacturaCliente.value,
            derecho = true,
            largo = true,
            modifier = Modifier
                .weight(2f)
        ) {
            noFacturaCliente.value = it
        }
        SelecionarFecha(
            "Fecha Inicial",
            datePickerState1.selectedDateMillis,
            modifierPadre = Modifier
                .padding(start = 20.dp)
                .weight(1f),
            modifierHijo = Modifier
                .padding(top = 5.dp)
                .width(200.dp)
                .height(55.dp),
            size = 16,
            fechaString = fechaIni
        ) {
            showDatePicker1.value = true
        }
        SelecionarFecha(
            "Fecha Final",
            datePickerState2.selectedDateMillis,
            modifierPadre = Modifier
                .padding(start = 20.dp)
                .weight(1f),
            modifierHijo = Modifier
                .padding(top = 5.dp)
                .width(200.dp)
                .height(55.dp),
            size = 16,
            fechaString = fechaFin
        ) {
            showDatePicker2.value = true
        }
    }
}