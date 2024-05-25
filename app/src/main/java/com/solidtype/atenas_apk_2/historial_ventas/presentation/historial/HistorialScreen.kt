package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets.DropdownSelect
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.formatoDDBB
import com.solidtype.atenas_apk_2.util.formatoParaUser
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.BotonBlanco
import com.solidtype.atenas_apk_2.util.ui.Components.DatePickerDialogo
import com.solidtype.atenas_apk_2.util.ui.Components.SelecionarFecha
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("SimpleDateFormat", "CoroutineCreationDuringComposition", "QueryPermissionsNeeded")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(navController: NavController, viewModel: HistorailViewModel = hiltViewModel()) {

    val context = LocalContext.current

    //val viewModel: HistorailViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val items = listOf("Ventas", "Ticket")

    val listVentas = uiState.Historial
    val listTicket = uiState.Ticket

    var totalVentas = uiState.total
    var totalTicket = uiState.total2

//    var ventasOTicket = uiState.ventasOTicket

    var ventasTickerTitulo by rememberSaveable { mutableStateOf("Ventas") } //Inmutable
    var selected by rememberSaveable { mutableStateOf("Ventas") }//Inmutable

    val datePickerState1: DatePickerState = rememberDatePickerState()
    var showDatePicker1 by rememberSaveable { mutableStateOf(false) }
    var fechaIni by rememberSaveable { mutableStateOf("") }

    val datePickerState2: DatePickerState = rememberDatePickerState()
    var showDatePicker2 by rememberSaveable { mutableStateOf(false) }
    var fechaFin by rememberSaveable { mutableStateOf("") }

    var showSnackbar by rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var snackbarJob: Job by remember { mutableStateOf(Job()) }

    var showSnackbarIni by rememberSaveable { mutableStateOf(false) }

    val categoria = listOf(
        "Celular",
        "Tablet",
        "Laptop",
        "Accesorios",
        "Jabón",
        "Otros"
    ) //Debería venir del viewModel

    var selectedCategoria by rememberSaveable { mutableStateOf(categoria.first()) }

    val uri = uiState.uriPath

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
        if (showSnackbarIni) {
            showSnackbarIni = false
            snackbarJob.cancel() //Cancela el job anterior si existe
            showSnackbar = true
            snackbarJob = coroutineScope.launch {
                delay(10000L)
                showSnackbar = false
            }
        }
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
                            SelecionarFecha(
                                "Fecha Inicial",
                                datePickerState1.selectedDateMillis,
                                fechaIni
                            ) {
                                showDatePicker1 = true
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            SelecionarFecha(
                                "Fecha Final",
                                datePickerState2.selectedDateMillis,
                                fechaFin
                            ) {
                                showDatePicker2 = true
                            }
                        }
                        //Aquí va un selector
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
                                    fechaIni.formatoDDBB(),
                                    fechaFin.formatoDDBB()
                                )
                                viewModel.buscarProductosTicket(
                                    fechaIni.formatoDDBB(),
                                    fechaFin.formatoDDBB()
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
                                        viewModel.MostrarHistoriar()//Se debe lanzar un evento
//                                        ventasOTicket = false
                                    }

                                    "Ticket" -> {
                                        ventasTickerTitulo = "Cuenta x Cobrar"
                                        totalTicket = uiState.total2
                                        viewModel.mostrarTicket()// se debe lanzar un evento de consulta
//                                        ventasOTicket = true
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color(0xFFFFFFFF), RoundedCornerShape(16.dp))
                ) {
                    when (selected) {
                        "Ventas" -> { //selected == "Ventas"
                            //Aquí va el menú de ventas
                            Row {
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
                            LazyColumn {
                                items(listVentas.size) { index ->
                                    Row {
                                        Text(
                                            listVentas[index].id_venta.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listVentas[index].id_cliente.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )

                                        Text(
                                            listVentas[index].fecha.toString().formatoParaUser(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )

                                        Text(
                                            listVentas[index].subtotal.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listVentas[index].cantidad.toString(),
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
                            LazyColumn {
                                items(listTicket.size) { index ->
                                    Row {
                                        Text(
                                            listTicket[index].id_ticket.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].descripcion,
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].fecha_inicio.toString()
                                                .formatoParaUser(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].fecha_final.toString()
                                                .formatoParaUser(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].subtotal.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].impuesto.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].total.toString(),
                                            fontSize = 16.sp,
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            listTicket[index].id_cliente.toString(),
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
                        Boton("Ver Todo") {
                            viewModel.MostrarHistoriar()
                            viewModel.mostrarTicket()
                            // limpiar fechas
                            fechaIni = ""
                            fechaFin = ""
                        }
                        Boton("Exportar") {
                            Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
                                .show()
                            viewModel.Exportar()
                            showSnackbarIni = true
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
                    fechaIni = datePickerState1.selectedDateMillis.formatearFecha()

                    viewModel.buscarProductosTicket(
                        fechaIni.formatoDDBB(),
                        fechaFin.formatoDDBB()
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
                    fechaFin = datePickerState2.selectedDateMillis.formatearFecha()
                    viewModel.buscarProductosVenta(
                        fechaIni.formatoDDBB(),
                        fechaFin.formatoDDBB()
                    )
                    viewModel.buscarProductosTicket(
                        fechaIni.formatoDDBB(),
                        fechaFin.formatoDDBB()
                    )
                    Toast.makeText(context, "No olvides selecionar las fechas.", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }
        AnimatedVisibility(
            visible = showSnackbar,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(500)
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Snackbar(
                    action = {
                        if (uri.isNotBlank()) {
                            Row {
                                BotonBlanco("Compartir") {
                                    val fileUri = Uri.parse(uri)
                                    val shareIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_STREAM, fileUri)
                                        type =
                                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                    }
                                    val chooser =
                                        Intent.createChooser(shareIntent, "Compartir archivo")
                                    context.startActivity(chooser)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                BotonBlanco("Ver") {
                                    val fileUri = Uri.parse(uri)
                                    val openIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_VIEW
                                        setDataAndType(
                                            fileUri,
                                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                        )
                                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    }
                                    val chooser = Intent.createChooser(openIntent, "Abrir archivo")
                                    if (openIntent.resolveActivity(context.packageManager) != null) {
                                        context.startActivity(chooser)
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "No se encontró una aplicación para abrir este archivo. Favor de instalar una aplicación compatible con archivos de Excel.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.padding(8.dp),
                    containerColor = Color(0xFF343341)
                ) {
                    Text(
                        text = if (uri.isNotBlank()) "El archivo se guardó en: $uri" else "Hubo un error al exportar",
                        color =
                        if (uri.isNotBlank())
                            Color(0xFF77FF77)
                        else
                            Color(0xFFFF7777),
                    )
                }
            }
        }
    }
}