package com.solidtype.atenas_apk_2.servicios.presentation.ticket

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.AreaUsuarios
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.historial_ventas.presentation.componets.AreaVentas
import com.solidtype.atenas_apk_2.historial_ventas.presentation.componets.AvatarConBotones
import com.solidtype.atenas_apk_2.historial_ventas.presentation.componets.DatePickerDialogoSimple
import com.solidtype.atenas_apk_2.historial_ventas.presentation.componets.Inputs
import com.solidtype.atenas_apk_2.historial_ventas.presentation.componets.Tabla
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.OnTicket
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo

@Composable
fun ticketSeen(viewModel: TicketViewModel = hiltViewModel()){
    val busqueda = rememberSaveable { mutableStateOf("") }

    val rol = rememberSaveable { mutableStateOf("") }
    val idUsuario = rememberSaveable { mutableStateOf("") }


    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.tickets.isEmpty()){
        viewModel.onEvent(TicketEvents.GetTickets)
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

            Row {
                Titulo("Ticket", Icons.Outlined.HistoryEdu)
                Buscador(busqueda.value) { busqueda.value = it
                viewModel.onEvent(
                    TicketEvents.BuscarTicket( it )
                )
                }
//                AreaUsuarios()
            }
            Spacer(modifier = Modifier.height(16.dp))
            tablatick(listaT = state.tickets )

        }

    }

}