package com.solidtype.atenas_apk_2.facturacion.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.FactCheck
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.BotonesFinales
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.DatePickerDialogoSimple
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.Inputs
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.Tabla
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.components.Loading
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.components.Titulo

@SuppressLint("MutableCollectionMutableState", "SuspiciousIndentation")
@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable
fun FacturacionScreen(navController: NavController, viewModel: FacturaViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val datePickerState1: DatePickerState = rememberDatePickerState()
    val showDatePicker1 = rememberSaveable { mutableStateOf(false) }

    val datePickerState2: DatePickerState = rememberDatePickerState()
    val showDatePicker2 = rememberSaveable { mutableStateOf(false) }

    //las tres entradas
    val fechaIni = rememberSaveable { mutableStateOf("") }
    val fechaFin = rememberSaveable { mutableStateOf("") }
    val noFacturaCliente = rememberSaveable { mutableStateOf("") }

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
        Column(
            //All
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(GrisClaro),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 100.dp, vertical = 20.dp)
            ) {//Contenedor
                Column {
                    Titulo("Facturas", Icons.AutoMirrored.Outlined.FactCheck)
                    Inputs(
                        noFacturaCliente,
                        datePickerState1,
                        fechaIni.value,
                        showDatePicker1,
                        datePickerState2,
                        fechaFin.value,
                        showDatePicker2
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Tabla(facturas = uiState.facturaConDetalle)
                    BotonesFinales(viewModel, fechaFin, fechaIni, noFacturaCliente)
                }
            }
        }
        DatePickerDialogoSimple(showDatePicker1, datePickerState1, fechaIni)
        DatePickerDialogoSimple(showDatePicker2, datePickerState2, fechaFin)
        MenuLateral(navController)
    }
}