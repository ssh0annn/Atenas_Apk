package com.solidtype.atenas_apk_2.facturacion.presentation.facturacion

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.facturacion.presentation.FacturaViewModel
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.Tabla
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.Titulo
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.InputBlanco
import com.solidtype.atenas_apk_2.facturacion.presentation.facturas
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.DatePickerDialogo
import com.solidtype.atenas_apk_2.util.ui.Components.NavPlato
import com.solidtype.atenas_apk_2.util.ui.Components.SelecionarFecha
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.fomatoLocalDate
import com.solidtype.atenas_apk_2.util.formatoDDBB

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable
fun FacturacionScreen(navController: NavController, viewModel: FacturaViewModel = hiltViewModel()) {

    //val viewModel: FacturaViewModel = hiltViewModel()

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val datePickerState1: DatePickerState = rememberDatePickerState()
    var showDatePicker1 by rememberSaveable { mutableStateOf(false) }

    val datePickerState2: DatePickerState = rememberDatePickerState()
    var showDatePicker2 by rememberSaveable { mutableStateOf(false) }

    //las tres entradas
    var fechaIni by rememberSaveable { mutableStateOf("") }
    var fechaFin by rememberSaveable { mutableStateOf("") }
    var noFacturaCliente by rememberSaveable { mutableStateOf("") }

    //val facturas = uiState.facturas
    val detalles = uiState.detalles

    Log.i("uiState.facturas", uiState.facturas.toString())
    Log.i("uiState.detalles", uiState.detalles.toString())
    Log.i("uiState.buscar", uiState.buscar.toString())

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
                    color = GrisClaro
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 100.dp, vertical = 20.dp)
            ) {//Contenedor
                Column {//Entradas, Datos y Botón
                    Titulo()
                    //Spacer(modifier = Modifier.height(25.dp))
                    Row {//Entradas
                        InputBlanco(
                            placeholder = "Buscar por Cliente o No. de Factura...",
                            valor = noFacturaCliente,
                            derecho = true,
                            largo = true,
                            modifier = Modifier
                                .weight(2f)
                        ) {
                            noFacturaCliente = it
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
                            showDatePicker1 = true
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
                            showDatePicker2 = true
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Tabla(facturas = facturas)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) { //Botón "Filtrar"
                        Boton("Ver Todos"){
                            viewModel.mostrarFactura()
                            fechaFin = ""
                            fechaIni = ""
                        }
                        Boton("Filtrar", fechaFin.isNotEmpty() && fechaIni.isNotEmpty()) {
                            viewModel.buscarfacturas(
                                fechaIni.fomatoLocalDate(),
                                fechaFin.fomatoLocalDate(),
                                noFacturaCliente
                            )
                        }
                    }
                }
            }
        }
        //NavPlato("factura", navController)
        DatePickerDialogo(
            showDatePicker = showDatePicker1,
            datePickerState = datePickerState1,
            onDismissRequest = {
                showDatePicker1 = false
            },
            onClick = {
                showDatePicker1 = false
                fechaIni = datePickerState1.selectedDateMillis.formatearFecha()
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
                fechaFin = datePickerState2.selectedDateMillis.formatearFecha()
            }
        )
    }
}