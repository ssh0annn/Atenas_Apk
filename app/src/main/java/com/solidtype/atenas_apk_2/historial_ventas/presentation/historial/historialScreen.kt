package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.BotonBlanco
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.DatePickerDialogo
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.DropdownSelect
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.SelecionarFecha
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen() {

    val items = listOf("Ventas", "Ticket")

    var ventas_ticker by rememberSaveable { mutableIntStateOf(10000) }
    var selected by rememberSaveable { mutableStateOf("") }

    val datePickerState1: DatePickerState =
        rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    var showDatePicker1 by rememberSaveable {
        mutableStateOf(false)
    }

    val datePickerState2: DatePickerState =
        rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    var showDatePicker2 by rememberSaveable {
        mutableStateOf(false)
    }



    Column(
        //Todo
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                Color(0xFFD7D7D9)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            //Contenedor
            modifier = Modifier
                .padding(top = 32.dp)
                .width(950.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Reporte", fontSize = 32.sp, fontWeight = FontWeight.Bold
            ) //Título
            Row {//Inpus y Area de Ventas
                Row(
                    modifier = Modifier.weight(3f), horizontalArrangement = Arrangement.SpaceBetween
                ) {//Inputs
                    SelecionarFecha("Fecha Inicial", datePickerState1.selectedDateMillis) {
                        showDatePicker1 = true
                    }
                    SelecionarFecha("Fecha Final", datePickerState2.selectedDateMillis) {
                        showDatePicker2 = true
                    }
                    //Aquí va un selector
                    Box(
                        modifier = Modifier.width(200.dp)
                    ) {
                        DropdownSelect(
                            items = items,
                            selectedItem = items.first(),
                        ) {
                            selected = it
                        }
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End,
                ) {//Area de Ventas
                    Text(
                        text = "Ventas", fontSize = 24.sp, fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$ventas_ticker RD$",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            ListItem(headlineContent = {
                //Aquí el menú
            })
        }
        DatePickerDialogo(
            showDatePicker = showDatePicker1,
            datePickerState = datePickerState1,
            onDismissRequest = {
                showDatePicker1 = false
            },
            onClick = {
                showDatePicker1 = false
            }
        )
        DatePickerDialogo(
            showDatePicker = showDatePicker2,
            datePickerState = datePickerState2,
            onDismissRequest = {
                showDatePicker2 = false
            },
            onClick = {
                showDatePicker2 = false
            }
        )
    }
}

//Preview para Vortex T10M (T10MPROPLUS) Horizontal
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun HistorialScreenPreview() {
    HistorialScreen()
}