package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.ui.theme.AzulGris

@Composable
fun MyProviderItem(
    Provider: Personastodas.Proveedor,
    mostrarDialogo: MutableState<Boolean>,
    editar: MutableState<Boolean>,
    nombre: MutableState<String>,
    Tipodocumento: MutableState<String>,
    Numdocumento: MutableState<String>,
    Email: MutableState<String>,
    Telefono: MutableState<String>,
    mostrarConfirmar: MutableState<Boolean>,
    idProveedor: MutableState<String>
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp),

        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ){
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Provider.id_proveedor.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f).padding(start = 40.dp), maxLines = 1
                )

                Text(
                    text = Provider.nombre.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f), maxLines = 1
                )
                Text(
                    text = Provider.documento.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )
                Text(
                    text = Provider.email.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )
                Text(
                    text = Provider.telefono.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )
                Row {
                    IconButton(onClick = {
                        mostrarDialogo.value = true
                        editar.value = true
                        //formulario onEdit
                        idProveedor.value = Provider.id_proveedor.toString()
                        nombre.value = Provider.nombre!!
                        Numdocumento.value = Provider.documento!!
                        Telefono.value = Provider.telefono!!
                        Email.value = Provider.email!!

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
                        idProveedor.value = Provider.id_proveedor.toString()
                        nombre.value = Provider.nombre!!
                        Numdocumento.value = Provider.documento!!
                        Telefono.value = Provider.telefono!!
                        Email.value = Provider.email!!
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