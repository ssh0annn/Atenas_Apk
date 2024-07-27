package com.solidtype.atenas_apk_2.historial_ventas.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.TipoUserSingleton
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.historial_ventas.presentation.componets.AreaVentas
import com.solidtype.atenas_apk_2.historial_ventas.presentation.componets.DatePickerDialogoSimple
import com.solidtype.atenas_apk_2.historial_ventas.presentation.componets.Inputs
import com.solidtype.atenas_apk_2.historial_ventas.presentation.componets.Tabla
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.formatoDDBB
import com.solidtype.atenas_apk_2.util.toLocalDate
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Loading
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.components.SnackbarAnimado
import com.solidtype.atenas_apk_2.util.ui.components.Titulo
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SimpleDateFormat", "CoroutineCreationDuringComposition", "QueryPermissionsNeeded")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(
    navController: NavController,
    viewModel: HistorailViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val ventasTickerTitulo = rememberSaveable { mutableStateOf("Ventas") }
    val selected = rememberSaveable { mutableStateOf("Ventas") }

    val datePickerStateList: List<DatePickerState> = listOf(
        rememberDatePickerState(),
        rememberDatePickerState()
    )
    val showDatePickerList: List<MutableState<Boolean>> = listOf(
        rememberSaveable { mutableStateOf(false) },
        rememberSaveable { mutableStateOf(false) }
    )
    val fechaList = listOf(
        rememberSaveable { mutableStateOf("") },
        rememberSaveable { mutableStateOf("") }
    )

    val identificador = rememberSaveable { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    var snackbarJob: Job by remember { mutableStateOf(Job()) }

    val showSnackbarList = listOf(
        rememberSaveable { mutableStateOf(false) },
        rememberSaveable { mutableStateOf(false) }
    )

    if (uiState.mensaje.isNotEmpty()) {
        Toast.makeText(context, uiState.mensaje, Toast.LENGTH_LONG).show()
        //viewModel.onEvent(HistorialEvent.LimpiarMensaje)
    }

    if (TipoUserSingleton.tipoUser != TipoUser.ADMIN) {
        navController.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Loading(true)
        }
    } else {
        if (showSnackbarList[1].value) {
            showSnackbarList[1].value = false
            snackbarJob.cancel() //Cancela el job anterior si existe
            showSnackbarList[0].value = true
            snackbarJob = coroutineScope.launch {
                delay(10000L)
                showSnackbarList[0].value = false
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
                    Inputs(
                        identificador, datePickerStateList[0], fechaList[0], showDatePickerList[0], datePickerStateList[1], fechaList[1], showDatePickerList[1], selected, ventasTickerTitulo,
                        getTodasVentas = {
                            viewModel.onEvent(
                                HistorialEvent.GetTodosTodasVentas
                            )
                        }, getTodosTickets = {
                            viewModel.onEvent(
                                HistorialEvent.GetTodosTickets
                            )
                        },
                        modifier = Modifier.weight(10f)
                    )
                    AreaVentas(ventasTickerTitulo, selected, uiState.total, uiState.abono, Modifier.weight(6f))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Tabla(selected, uiState.Historial, uiState.Ticket)
                Spacer(modifier = Modifier.height(12.dp))
                Box(//Botones
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) { //Avatar y Botones
                    Row {
                        Boton("Ver Todo") {
                            if (selected.value == "Ventas") {
                                viewModel.onEvent(
                                    HistorialEvent.GetTodosTodasVentas
                                )
                            } else {
                                viewModel.onEvent(
                                    HistorialEvent.GetTodosTickets
                                )
                            }
                            fechaList[0].value = ""
                            fechaList[1].value = ""
                        }
                        Boton("Exportar") {
                            Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
                                .show()
                            viewModel.onEvent(HistorialEvent.Exportar)
                            showSnackbarList[1].value = true
                        }
                    }
                }
            }
            DatePickerDialogoSimple(
                showDatePickerList[0],
                datePickerStateList[0],
            ){
                showDatePickerList[0].value = false
                when (identificador.value) {
                    "FechaIni" -> fechaList[0].value = datePickerStateList[0].selectedDateMillis.formatearFecha()
                    "FechaFin" -> fechaList[1].value = datePickerStateList[0].selectedDateMillis.formatearFecha()
                }
                when (selected.value) {
                    "Ventas" -> {
                        viewModel.onEvent(
                            HistorialEvent.GetVentasFechas(
                                desde = fechaList[0].value.formatoDDBB().toLocalDate(),
                                hasta = fechaList[1].value.formatoDDBB().toLocalDate(),
                            )
                        )
                    }
                    "Ticket" -> {
                        viewModel.onEvent(
                            HistorialEvent.GetTicketsFechas(
                                desde = fechaList[0].value.formatoDDBB().toLocalDate(),
                                hasta = fechaList[1].value.formatoDDBB().toLocalDate()
                            )
                        )
                    }
                }
            }
            DatePickerDialogoSimple(
                showDatePickerList[1],
                datePickerStateList[1],
            ){
                showDatePickerList[1].value = false
                when (identificador.value) {
                    "FechaIni" -> fechaList[0].value = datePickerStateList[1].selectedDateMillis.formatearFecha()
                    "FechaFin" -> fechaList[1].value = datePickerStateList[1].selectedDateMillis.formatearFecha()
                }
                when (selected.value) {
                    "Ventas" -> {
                        viewModel.onEvent(
                            HistorialEvent.GetVentasFechas(
                                desde = fechaList[0].value.formatoDDBB().toLocalDate(),
                                hasta = fechaList[1].value.formatoDDBB().toLocalDate(),
                            )
                        )
                    }
                    "Ticket" -> {
                        viewModel.onEvent(
                            HistorialEvent.GetTicketsFechas(
                                desde = fechaList[0].value.formatoDDBB().toLocalDate(),
                                hasta = fechaList[1].value.formatoDDBB().toLocalDate()
                            )
                        )
                    }
                }
            }
        }
        MenuLateral(navController)
        SnackbarAnimado(showSnackbarList[0].value, uiState.uriPath, context)
    }
}