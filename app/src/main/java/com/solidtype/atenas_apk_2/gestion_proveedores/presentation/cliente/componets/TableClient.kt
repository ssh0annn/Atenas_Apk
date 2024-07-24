package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClienteStateUI
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClientesViewModel
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun TableClients(
    clients: List<Personastodas.ClienteUI>,
    mostrarDialogo: MutableState<Boolean>,
    editar: MutableState<Boolean>,
    nombre: MutableState<String>,
    tipoDocumento: MutableState<String>,
    numDocumento: MutableState<String>,
    email: MutableState<String>,
    telefono: MutableState<String>,
    idCliente: MutableState<String>,
    uiState: ClienteStateUI,
    mostrarDialogoG: MutableState<Boolean>,
    confirmarMensaje: MutableState<String>,
    accionDeConfirmacion: MutableState<() -> Unit>,
    viewModel: ClientesViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Pantalla.alto - 250.dp)
            .background(AzulGris, shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .background(GrisOscuro, shape = RoundedCornerShape(20.dp))
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ID",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Nombre",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Documento",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Email",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Telefono",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "",
                    modifier = Modifier.weight(0.5f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
            }
            LazyColumn(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
                    .background(GrisOscuro)
            ) {
                items(clients) { cliente ->
                    MyClientItem(
                        client = cliente,
                        mostrarDialogo = mostrarDialogo,
                        editar = editar,
                        nombre,
                        tipoDocumento,
                        numDocumento,
                        email,
                        telefono,
                        idCliente,
                        uiState,
                        mostrarDialogoG,
                        confirmarMensaje,
                        accionDeConfirmacion,
                        viewModel
                    )
                }
            }
        }
    }
}



