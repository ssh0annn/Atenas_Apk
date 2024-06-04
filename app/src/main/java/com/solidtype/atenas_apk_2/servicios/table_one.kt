package com.solidtype.atenas_apk_2.servicios

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.Tabla
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.InputBlanco
import com.solidtype.atenas_apk_2.facturacion.presentation.facturas
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiceEvent
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiciosViewModel
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.DatePickerDialogo
import com.solidtype.atenas_apk_2.util.ui.Components.NavPlato
import com.solidtype.atenas_apk_2.util.ui.Components.SelecionarFecha


@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMultiplatform::class)
fun complementari(viewmodel: ServiciosViewModel = hiltViewModel()) {

    val state by viewmodel.uiStates.collectAsStateWithLifecycle()
    if(state.listaServicios.isEmpty()){
        viewmodel.onEvent(ServiceEvent.GetServicios)
    }

    Column(
        //To.do
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp)
//            .verticalScroll(rememberScrollState())
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
            Tituloserv()
            Column {
                Spacer(modifier = Modifier.height(70.dp))

                tablaserv(listaTiket = state.listaTickets)


            }
        }
      //primera opcion

    }


    selector(viewmodel, state.listaServicios)
    //NavPlato("Servicios")

}


