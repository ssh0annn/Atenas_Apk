package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro


@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMultiplatform::class)
fun complementari(viewmodel: ServiciosViewModel = hiltViewModel()) {

    val state by viewmodel.uiStates.collectAsStateWithLifecycle()

    if(state.listaTickets.isEmpty()){
        viewmodel.onTicket(OnTicket.GetTickets)
    }
    if(state.listaServicios.isEmpty()){
        viewmodel.onServiceEvent(ServiceEvent.GetServicios)
    }
    if(state.listaClientes.isEmpty()){
        viewmodel.onCliente(ClientEvents.GetClientes)
    }
    if(state.listaDispositivos.isEmpty()){
        viewmodel.onDevice(DeviceEvent.GetDispositivos)
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


    selector(viewmodel, state.listaServicios, state.listaClientes, state.listaDispositivos)
//    NavPlato("Servicios")

}


