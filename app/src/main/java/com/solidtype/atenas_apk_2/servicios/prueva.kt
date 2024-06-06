package com.solidtype.atenas_apk_2.servicios

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiceEvent
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiciosViewModel
import org.w3c.dom.Text


@Composable
fun dale(
    viewmodel: ServiciosViewModel = hiltViewModel()
) {

    val state by viewmodel.uiStates.collectAsStateWithLifecycle()
    var nombre by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }

    if (state.listaClientes.isEmpty()) {
        viewmodel.onEvent(ServiceEvent.GetClientes)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.listaClientes) { cliente ->

            card(clienteUI = cliente!!) {
//                viewmodel.onEvent(ServiceEvent.prueva(cliente))
                nombre = cliente.nombre.toString()
                telefono = cliente.telefono.toString()
            }


        }
    }

    println(nombre + telefono)

}

//@Composable
//fun card(clienteUI: Personastodas.ClienteUI, onclick: () -> Unit) {
//
//    Box(
//        modifier = Modifier.clickable(onClick = onclick)
//    ) {
//        Text(text = clienteUI.nombre.toString())
//    }
//
//
//}