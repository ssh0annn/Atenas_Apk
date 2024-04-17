package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.DatePickerDialogo
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.DropdownSelect
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.SelecionarFecha
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(/*navController: NavController, viewModel:HistorailViewModel= hiltViewModel()*/) {

    val context = LocalContext.current
    val viewModel: HistorailViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val items = listOf("Ventas", "Ticket")

    val listVentas = uiState.Historial
    val listTicket = uiState.Ticket

    var totalVentas = uiState.total
    var totalTicket = uiState.total2

    var uri: String

    var ventasTickerTitulo by rememberSaveable { mutableStateOf("Ventas") } //Inmutable
    var selected by rememberSaveable { mutableStateOf("Ventas") }//Inmutable

    val datePickerState1: DatePickerState = rememberDatePickerState()
    var showDatePicker1 by rememberSaveable { mutableStateOf(false) }
    var fechaIni by rememberSaveable { mutableStateOf("") }

    val datePickerState2: DatePickerState = rememberDatePickerState()
    var showDatePicker2 by rememberSaveable { mutableStateOf(false) }
    var fechaFin by rememberSaveable { mutableStateOf("") }

    val categoria = listOf(
        "Celular",
        "Tablet",
        "Laptop",
        "Accesorios",
        "Jabón",
        "Otros"
    )
    var selectedCategoria by rememberSaveable { mutableStateOf("Celular") }


    if (false) {
        //nav.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
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
                        modifier = Modifier.weight(3f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {//Inputs
                        Row(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .weight(2f)
                        ) {
                            SelecionarFecha("Fecha Inicial", datePickerState1.selectedDateMillis) {
                                showDatePicker1 = true
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            SelecionarFecha("Fecha Final", datePickerState2.selectedDateMillis) {
                                showDatePicker2 = true
                            }
                        }
                        //Aquí va un selectores
                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .weight(1f)
                        ) {
                            DropdownSelect(
                                items = categoria,
                                selectedItem = selectedCategoria,
                            ) {
                                selectedCategoria = it
                                viewModel.buscarProductosVenta(
                                    formatoDDBB(fechaIni),
                                    formatoDDBB(fechaFin),
                                    selectedCategoria
                                )
                                viewModel.buscarProductosTicket(
                                    formatoDDBB(fechaIni),
                                    formatoDDBB(fechaFin),
                                    selectedCategoria
                                )
                                Toast.makeText(
                                    context,
                                    "No olvides selecionar las fechas.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .weight(1f)
                        ) {
                            DropdownSelect(
                                items = items,
                                selectedItem = selected,
                            ) {
                                selected = it
                                when (selected) {
                                    "Ventas" -> {
                                        ventasTickerTitulo = "Ventas"
                                        totalVentas = uiState.total
                                    }

                                    "Ticket" -> {
                                        ventasTickerTitulo = "Cuenta x Cobrar"
                                        totalTicket = uiState.total2
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.End,
                    ) {//Area de Ventas
                        Text(
                            text = ventasTickerTitulo,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = when (selected) {
                                "Ventas" -> "$totalVentas RD$"
                                "Ticket" -> "$totalTicket RD$"
                                else -> "0.0 RD$"
                            },
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                //Aquí el menú de ventas o ticket
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color(0xFFFFFFFF), RoundedCornerShape(16.dp))
                ) {
                    when (selected) {
                        "Ventas" -> { //selected == "Ventas"
                            //Aquí va el menú de ventas
                            LazyColumn {
                                item {
                                    Row(
                                        modifier = Modifier
                                            .clickable {
                                                // Aquí lógica para ordenar por ID
                                            }
                                    ) {
                                        Text(
                                            "Código",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Producto",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Fecha",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Precio",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Cantidad",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Cliente",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Divider()
                                }
                                items(listVentas.size) { index ->
                                    Row {
                                        Text(
                                            listVentas[index].Codigo.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listVentas[index].Nombre,
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listVentas[index].FechaIni,
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listVentas[index].Precio.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listVentas[index].Cantidad.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listVentas[index].NombreCliente,
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Divider()
                                }
                            }
                        }

                        "Ticket" -> {
                            //Aquí va el menú de ticket
                            LazyColumn {
                                item {
                                    Row {
                                        Text(
                                            "Código",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Estado",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Fecha_Ini",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Fecha_Fin",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Precio",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Restante",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Abono",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Cliente",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Divider()
                                }
                                items(listTicket.size) { index ->
                                    Row {
                                        Text(
                                            listTicket[index].Codigo.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].EstadoEquipo,
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].FechaInicial,
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].FechaFinal,
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].Precio.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].Restante.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].Abono.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].NombreCliente,
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    Divider()
                                }
                            }
                        }

                        else -> {
                            Text(text = "Seleccione un tipo de reporte", fontSize = 24.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row { //Avatar y Botones
                    //Avatar
                    if (true) { // si no hay imagen de perfil
                        Avatar()
                    } else {//Mostrar foto de perfil
                        //Image(painter = , contentDescription = )
                    }
                    Spacer(modifier = Modifier.width(400.dp))
                    Row {
                        //Botones para Importar, Exportar y Ver
                        Boton("Cancelar") {
                            //Aquí lógica para cancelar
                        }
                        Boton("Exportar") {
                            //Aquí lógica para exportar
                            viewModel.Exportar()
                            uri = uiState.uriPath
                            if (uri.isNotEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Exportado con éxito en: $uri",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(context, "Error al exportar", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
            DatePickerDialogo(
                showDatePicker = showDatePicker1,
                datePickerState = datePickerState1,
                onDismissRequest = {
                    showDatePicker1 = false
                },
                onClick = {
                    showDatePicker1 = false
                    fechaIni = formatearFecha(datePickerState1.selectedDateMillis)
                    viewModel.buscarProductosTicket(
                        formatoDDBB(fechaIni),
                        formatoDDBB(fechaFin),
                        selectedCategoria
                    )
                    Toast.makeText(context, "No olvides selecionar las fechas.", Toast.LENGTH_SHORT)
                        .show()
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
                    fechaFin = formatearFecha(datePickerState2.selectedDateMillis)
                    viewModel.buscarProductosVenta(
                        formatoDDBB(fechaIni),
                        formatoDDBB(fechaFin),
                        selectedCategoria
                    )
                    viewModel.buscarProductosTicket(
                        formatoDDBB(fechaIni),
                        formatoDDBB(fechaFin),
                        selectedCategoria
                    )
                    Toast.makeText(context, "No olvides selecionar las fechas.", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun formatearFecha(fecha: Long?): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    return sdf.format(Date(fecha!!))
}

fun formatoDDBB(fecha: String): String {
    val array = fecha.split("/")
    return "${array[2]}-${array[1]}-${array[0]}"
}