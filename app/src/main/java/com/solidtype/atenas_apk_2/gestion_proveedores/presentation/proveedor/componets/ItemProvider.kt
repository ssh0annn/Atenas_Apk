package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor.componets

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
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor.ProveedorStatesUI
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco

@Composable
fun MyProviderItem(
    provider: Personastodas.Proveedor,
    mostrarDialogo: MutableState<Boolean>,
    editar: MutableState<Boolean>,
    nombre: MutableState<String>,
    numDocumento: MutableState<String>,
    email: MutableState<String>,
    telefono: MutableState<String>,
    mostrarConfirmar: MutableState<Boolean>,
    idProveedor: MutableState<String>,
    tipoDocumento: MutableState<String>,
    direccion: MutableState<String>,
    uiState: ProveedorStatesUI
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
                    text = provider.id_proveedor.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f), maxLines = 1,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = provider.nombre.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f), maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = provider.documento.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = provider.email.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = provider.telefono.toString(),
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
                            mostrarConfirmar.value = true

                            idProveedor.value = provider.id_proveedor.toString()
                            nombre.value = provider.nombre!!
                            numDocumento.value = provider.documento!!
                            telefono.value = provider.telefono!!
                            email.value = provider.email!!
                            tipoDocumento.value = provider.tipo_documento!!
                            direccion.value = provider.direccion!!
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
                            idProveedor.value = provider.id_proveedor.toString()
                            nombre.value = provider.nombre!!
                            numDocumento.value = provider.documento!!
                            telefono.value = provider.telefono!!
                            email.value = provider.email!!
                            tipoDocumento.value = provider.tipo_documento!!
                            direccion.value = provider.direccion!!
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null,
                                tint = AzulGris
                            )
                        }
                        IconButton(onClick = {
                            mostrarConfirmar.value = true
                            //formulario onDelete
                            idProveedor.value = provider.id_proveedor.toString()
                            nombre.value = provider.nombre!!
                            numDocumento.value = provider.documento!!
                            telefono.value = provider.telefono!!
                            email.value = provider.email!!
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