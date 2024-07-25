package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClienteEvent
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClienteStateUI
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClientesViewModel
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.util.ui.components.confirmar

@Composable
fun MyClientItem(
    client: Personastodas.ClienteUI,
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
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Blanco, RoundedCornerShape(16.dp))
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = client.id_cliente.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f), maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = client.nombre.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f), maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = client.documento.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = client.email.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = client.telefono.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.weight(0.5f)
                ) {
                    if(uiState.switch) {
                        IconButton(onClick = {
                            {
                                editar.value = false
                                viewModel.onUserEvent(
                                ClienteEvent.RestaurarClientes/*(
                                        Personastodas.ClienteUI(
                                            client.id_cliente,
                                            client.nombre,
                                            client.tipo_documento,
                                            client.documento,
                                            client.email,
                                            client.telefono
                                        )
                                    )*/
                                )
                                mostrarDialogoG.value = false
                            }.confirmar(
                                mensaje = "¿Estás seguro que deseas restaurar este cliente?",
                                showDialog = { mostrarDialogoG.value = true },
                                setMessage = { confirmarMensaje.value = it },
                                setAction = { accionDeConfirmacion.value = it }
                            )
                        }){
                            Icon(
                                imageVector = Icons.Filled.RestoreFromTrash,
                                contentDescription = null,
                                tint = AzulGris
                            )
                        }
                    }
                    else {
                        IconButton(onClick = {
                            mostrarDialogo.value = true
                            editar.value = true

                            //formulario onEdit
                            idCliente.value = client.id_cliente.toString()
                            nombre.value = client.nombre!!
                            tipoDocumento.value = client.tipo_documento!!
                            numDocumento.value = client.documento!!
                            telefono.value = client.telefono!!
                            email.value = client.email!!

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null,
                                tint = AzulGris
                            )
                        }
                        IconButton(onClick = {
                            {
                                viewModel.onUserEvent(
                                    ClienteEvent.BorrarClientes(
                                        Personastodas.ClienteUI(
                                            client.id_cliente,
                                            client.nombre,
                                            client.tipo_documento,
                                            client.documento,
                                            client.email,
                                            client.telefono
                                        )
                                    )
                                )
                                mostrarDialogoG.value = false
                            }.confirmar(
                                mensaje = "¿Estás seguro que deseas eliminar el cliente '${client.nombre}'?",
                                showDialog = { mostrarDialogoG.value = true },
                                setMessage = { confirmarMensaje.value = it },
                                setAction = { accionDeConfirmacion.value = it }
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = null,
                                tint = AzulGris
                            )
                        }
                    }
                }
            }
        }
    }
}