package com.solidtype.atenas_apk_2.facturacion.presentation

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.FactCheck
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.DatePickerDialogoSimple
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Inputs
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Tabla
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.fomatoLocalDate
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Loading
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.components.Titulo

@SuppressLint("MutableCollectionMutableState", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacturacionScreen(
    navController: NavController,
    viewModel: FacturaViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val noFacturaCliente = rememberSaveable { mutableStateOf("") }

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

    if (uiState.mensaje.isNotEmpty()) {
        Toast.makeText(context, uiState.mensaje, Toast.LENGTH_LONG).show()
        viewModel.onEvent(FacturasEvent.LimpiarMensaje)
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
                        datePickerStateList,
                        fechaList,
                        showDatePickerList
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Tabla(facturas = uiState.facturaConDetalle)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Boton("Ver Todos") {
                            viewModel.onEvent(FacturasEvent.GetFacturas)
                            fechaList[0].value = ""
                            fechaList[1].value = ""
                        }
                        Boton(
                            "Filtrar",
                            fechaList[1].value.isNotEmpty() &&
                                    fechaList[0].value.isNotEmpty() &&
                                    noFacturaCliente.value.isNotEmpty()
                        ) {
                            viewModel.onEvent(
                                FacturasEvent.BuscarFacturas(
                                    noFacturaCliente.value,
                                    fechaList[0].value.fomatoLocalDate(),
                                    fechaList[1].value.fomatoLocalDate(),
                                )
                            )
                        }
                    }
                }
            }
        }
        DatePickerDialogoSimple(showDatePickerList[0], datePickerStateList[0], fechaList[0])
        DatePickerDialogoSimple(showDatePickerList[1], datePickerStateList[1], fechaList[1])
        MenuLateral(navController)
    }
}