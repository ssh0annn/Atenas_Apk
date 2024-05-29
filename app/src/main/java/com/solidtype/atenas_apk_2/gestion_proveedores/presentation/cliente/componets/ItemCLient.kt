package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemClient(
    modifier: Modifier = Modifier,
    Client: Map<String,Any>,
    onEditUser: () -> Unit,
    onDeleteUser: () -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp),

        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ){

        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,


        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = Client["nombre"].toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f), maxLines = 1
                )
                Text(
                    text = Client["documento"].toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )
                Text(
                    text = Client["direccion"].toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )
                Text(
                    text = Client["email"].toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )
                Text(
                    text = Client["telefono"].toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1
                )



                Row {
                    IconButton(onClick = onEditUser) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = Color.Green
                        )
                    }

                    IconButton(onClick = onDeleteUser) {
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

    val mapClient = mapOf(
        "nombre" to "Johan Diaz",
        "documento" to "84864",
        "direccion" to "calle 4",
        "email" to "therealdiaz@live,com",
        "telefono" to "8096134992",
    )

    MaterialTheme {
        ItemClient(
            Client = mapClient,
            onEditUser = {},
            onDeleteUser = {}
        )
    }
}