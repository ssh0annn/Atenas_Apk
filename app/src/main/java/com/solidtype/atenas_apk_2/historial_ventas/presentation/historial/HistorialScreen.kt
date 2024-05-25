package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.AreaVentas
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.AvatarConBotones
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.DatePickerDialogoSimple
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.Inputs
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.SnackbarAnimado
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.Tabla
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SimpleDateFormat", "CoroutineCreationDuringComposition", "QueryPermissionsNeeded")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(navController: NavController, viewModel: HistorailViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val ventasTickerTitulo = rememberSaveable { mutableStateOf("Ventas") } //Inmutable
    val selected = rememberSaveable { mutableStateOf("Ventas") }//Inmutable

    val datePickerState1: DatePickerState = rememberDatePickerState()
    val showDatePicker1 = rememberSaveable { mutableStateOf(false) }
    val fechaIni = rememberSaveable { mutableStateOf("") }

    val datePickerState2: DatePickerState = rememberDatePickerState()
    val showDatePicker2 = rememberSaveable { mutableStateOf(false) }
    val fechaFin = rememberSaveable { mutableStateOf("") }

    var showSnackbar by rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var snackbarJob: Job by remember { mutableStateOf(Job()) }

    val showSnackbarIni = rememberSaveable { mutableStateOf(false) }

    val categoria = listOf(
        "Celular",
        "Tablet",
        "Laptop",
        "Accesorios",
        "Jabón",
        "Otros"
    ) //Debería venir del viewModel

    val selectedCategoria by rememberSaveable { mutableStateOf(categoria.first()) }

    if (false) {
        navController.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        if (showSnackbarIni.value) {
            showSnackbarIni.value = false
            snackbarJob.cancel() //Cancela el job anterior si existe
            showSnackbar = true
            snackbarJob = coroutineScope.launch {
                delay(10000L)
                showSnackbar = false
            }
        }
        Column(
            //All
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(GrisClaro),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                //Contenedor
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 100.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Titulo("Reporte", Icons.Outlined.HistoryEdu)
                Row {
                    Inputs(datePickerState1, fechaIni, showDatePicker1, datePickerState2, fechaFin, showDatePicker2, selected, ventasTickerTitulo, viewModel, modifier = Modifier.weight(4f))
                    AreaVentas(ventasTickerTitulo, selected, uiState, Modifier.weight(3f))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Tabla(selected, uiState)
                Spacer(modifier = Modifier.height(12.dp))
                AvatarConBotones(viewModel, fechaIni, fechaFin, context, showSnackbarIni)
            }
            DatePickerDialogoSimple(showDatePicker1, datePickerState1, fechaIni, viewModel, fechaFin, selectedCategoria, context)
            DatePickerDialogoSimple(showDatePicker2, datePickerState2, fechaFin, viewModel, fechaIni, selectedCategoria, context)
        }
        SnackbarAnimado(showSnackbar, uiState, context)
    }
}