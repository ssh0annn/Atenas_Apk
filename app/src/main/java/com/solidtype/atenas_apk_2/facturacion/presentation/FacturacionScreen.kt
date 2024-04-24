package com.solidtype.atenas_apk_2.facturacion.presentation.facturacion

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.InputBlanco
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.formatearFecha
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.formatoDDBB
import com.solidtype.atenas_apk_2.util.ui.Components.DatePickerDialogo
import com.solidtype.atenas_apk_2.util.ui.Components.SelecionarFecha

@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable
fun FacturacionScreen(){

    val context = LocalContext.current

    var noFactura by rememberSaveable { mutableStateOf("") }
    var cliente by rememberSaveable { mutableStateOf("") }

    val datePickerState: DatePickerState = rememberDatePickerState()
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    var fecha by rememberSaveable { mutableStateOf("") }

    Column(
        //To.do
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                Color(0xFFD7D7D9)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp, vertical = 20.dp)
        ) {//Contenedor
            Row {//Título
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.FactCheck,
                    contentDescription = "",
                    tint = Color(0xFF343341),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(40.dp)
                )
                Text(
                    text = "Facturas",
                    color = Color(0xFF343341),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 25.dp)
            ){//Entradas, Datos y Tap
                Row {//Entradas
                    InputBlanco(
                        label = "No. Factura",
                        valor = noFactura,
                        derecho = true,
                        modifier = Modifier
                            .weight(10f)
                    ) {
                        noFactura = it
                    }
                    InputBlanco(
                        label = "Cliente",
                        valor = cliente,
                        derecho = true,
                        modifier = Modifier
                            .weight(5f)
                    ) {
                        cliente = it
                    }
                    SelecionarFecha(
                        "Fecha",
                        datePickerState.selectedDateMillis,
                        modifierPadre = Modifier
                            .padding(start = 20.dp)
                            .weight(5f),
                        modifierHijo = Modifier
                            .padding(top = 5.dp)
                            .width(200.dp)
                            .height(55.dp),
                        size = 16,
                    ) {
                        showDatePicker = true
                    }
                }
                Box{//Datos o Tabla

                }
                Box {//Botones

                }
            }
        }
        DatePickerDialogo(
            showDatePicker = showDatePicker,
            datePickerState = datePickerState,
            onDismissRequest = {
                showDatePicker = false
            },
            onClick = {
                showDatePicker = false
                fecha = formatearFecha(datePickerState.selectedDateMillis)

                /*viewModel.buscarFacturas(
                    formatoDDBB(fecha),
                    selectedCategoria
                )*/
            }
        )
    }
}

//Preview para Vortex T10M (T10MPROPLUS) Horizontal
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun FacturacionScreenPreview() {
    FacturacionScreen()
}