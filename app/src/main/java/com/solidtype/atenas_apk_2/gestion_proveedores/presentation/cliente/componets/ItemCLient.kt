package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas

@Composable
fun MyClientItem(
    Client: Personastodas.ClienteUI,
    mostrarDialogo: MutableState<Boolean>,
    editar: MutableState<Boolean>,
    nombre: MutableState<String>,
    Tipodocumento: MutableState<String>,
    Numdocumento: MutableState<String>,
    Email: MutableState<String>,
    Telefono: MutableState<String>,
    mostrarConfirmar: MutableState<Boolean>

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
                    text = Client.nombre.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f).padding(start = 40.dp), maxLines = 1
                )
                Text(
                    text = Client.documento.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )
                Text(
                    text = Client.email.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )
                Text(
                    text = Client.telefono.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )

                Row {
                    IconButton(onClick = {
                        mostrarDialogo.value = true
                        editar.value = true

                        //formulario onEdit
                        nombre.value = Client.nombre!!
                        Numdocumento.value = Client.documento!!
                        Telefono.value = Client.telefono!!
                        Email.value = Client.email!!

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = Color.Green
                        )
                    }

                    IconButton(onClick = {
                        mostrarConfirmar.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }
            }
        }

    }
}




@Preview(showBackground = true)
@Composable
fun PreviewClient() {

    val cliente : Personastodas.ClienteUI = Personastodas.ClienteUI(
        1,
        "Johan Diaz",
        "4848-+84",
        "809-659-8452",
        "therealdiaz@live.com",
    )

    MaterialTheme {
    }
}
