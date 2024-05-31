package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.FactCheck
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.BotonesFinales
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.Inputs
import com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals.Tabla
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets.MyClientItem
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets.TableClients
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets.TableContent
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets.bottonAddClient
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets.listClients
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Buscador
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Buscador2
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ClienteScreen(

     viewModel: ClientesViewModel = hiltViewModel()
){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var busqueda by rememberSaveable { mutableStateOf("") }
    var mostrar by rememberSaveable { mutableStateOf(false) }

    if (busqueda.isNotBlank()) {
        viewModel.onUserEvent(ClienteEvent.BuscarClientes(busqueda))
    } else {
        viewModel.onUserEvent(ClienteEvent.MostrarClientesEvent)
    }

    if (uiState.clientes.isEmpty()){
        viewModel.onUserEvent(ClienteEvent.MostrarClientesEvent)
    }



    Column(
        //All
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(GrisClaro),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp, vertical = 25.dp)
        ) {//Contenedor
            Column {

                Box {
                    Row {
                        Titulo("Clientes", Icons.AutoMirrored.Outlined.FactCheck)
                    }
                    Row(Modifier.padding(start = 400.dp, top = 50.dp, end = 20.dp)) {
                        Buscador2(busqueda = busqueda) {
                            busqueda = it
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                TableClients(uiState.clientes)
                Spacer(modifier = Modifier.height(40.dp))
                bottonAddClient(Modifier,{})

            }
        }
    }

}


@Preview(
    backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560
)
@Composable
fun previewTable(){
    MaterialTheme {

    }
}









