package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.DatePickerDialogo
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.DropdownSelect
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.SelecionarFecha
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(/*navController: NavController, viewModel:HistorailViewModel= hiltViewModel()*/) {

    val viewModel: HistorailViewModel = hiltViewModel() //luego se arregla los parámetros;
    // hice esto para poder probar la aplicación.

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val items = listOf("Ventas", "Ticket")

    /*val listVentas = listOf(
        objVenta(1, "01/01/2021", "Cliente 1", 1000.0, "Pagado"),
        objVenta(2, "02/01/2021", "Cliente 2", 2000.0, "Pendiente"),
        objVenta(3, "03/01/2021", "Cliente 3", 3000.0, "Pagado"),
        objVenta(4, "04/01/2021", "Cliente 4", 4000.0, "Pagado"),
        objVenta(5, "05/01/2021", "Cliente 5", 5000.0, "Pendiente"),
        objVenta(6, "06/01/2021", "Cliente 6", 6000.0, "Pagado"),
        objVenta(7, "07/01/2021", "Cliente 7", 7000.0, "Pagado"),
        objVenta(8, "08/01/2021", "Cliente 8", 8000.0, "Pendiente"),
        objVenta(9, "09/01/2021", "Cliente 9", 9000.0, "Pagado"),
        objVenta(10, "10/01/2021", "Cliente 10", 10000.0, "Pagado"),
    )*/ // esto debe venir del viewModel

    val listVentas = uiState.Historial

    /*val listTicket = listOf(
        objTicket(1, "01/02/2021", "Cliente 1", 1000.0, "Pagado"),
        objTicket(2, "02/02/2021", "Cliente 2", 2000.0, "Pendiente"),
        objTicket(3, "03/02/2021", "Cliente 3", 3000.0, "Pagado"),
        objTicket(4, "04/02/2021", "Cliente 4", 4000.0, "Pagado"),
        objTicket(5, "05/02/2021", "Cliente 5", 5000.0, "Pendiente"),
        objTicket(6, "06/02/2021", "Cliente 6", 6000.0, "Pagado"),
        objTicket(7, "07/02/2021", "Cliente 7", 7000.0, "Pagado"),
        objTicket(8, "08/02/2021", "Cliente 8", 8000.0, "Pendiente"),
        objTicket(9, "09/02/2021", "Cliente 9", 9000.0, "Pagado"),
        objTicket(10, "10/02/2021", "Cliente 10", 10000.0, "Pagado"),
    )*/ // esto debe venir del viewModel

    val listTicket = uiState.Ticket

    //var ventasTickerDinero by rememberSaveable { mutableStateOf(0.0) }
    var ventasTickerDinero = uiState.total

    var ventasTickerTitulo by rememberSaveable { mutableStateOf("Ventas") } //Inmutable
    var selected by rememberSaveable { mutableStateOf("Ventas") }//Inmutable

    val datePickerState1: DatePickerState =
        rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())//Inmutable

    var showDatePicker1 by rememberSaveable {
        mutableStateOf(false)
    }

    val datePickerState2: DatePickerState =
        rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())
    var showDatePicker2 by rememberSaveable {
        mutableStateOf(false)
    }

    //if
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
                                selectedItem = selected,
                            ) {
                                selected = it
                                when (selected) {
                                    "Ventas" -> {
                                        ventasTickerTitulo = "Ventas"
                                        ventasTickerDinero = uiState.total
                                    }

                                    "Ticket" -> {
                                        ventasTickerTitulo = "Cuenta x Cobrar"
                                        ventasTickerDinero = uiState.total
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
                            text = "$ventasTickerDinero RD$",
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
                                            "Fecha_Inicio",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Fecha_Final",
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
                                            listVentas[index].FechaFin,
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
                                            "Fecha_Inicial",
                                            fontSize = 20.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            "Fecha_Final",
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
                Row{ //Avatar y Botones
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
}

//Preview para Vortex T10M (T10MPROPLUS) Horizontal
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun HistorialScreenPreview() {

}

/*
data class objVenta(
    val id: Int,
    val fecha: String,
    val cliente: String,
    val total: Double,
    val estado: String
)

data class objTicket(
    val id: Int,
    val fecha: String,
    val cliente: String,
    val total: Double,
    val estado: String
)*/
