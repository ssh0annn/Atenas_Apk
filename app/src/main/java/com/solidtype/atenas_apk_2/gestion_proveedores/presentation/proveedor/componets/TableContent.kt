package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas

@Composable
fun TableContent(
    modifier: Modifier = Modifier,
    onDeleteUser: (proveedor: Personastodas.Proveedor) -> Unit,
    onEditUser: (proveedor: Personastodas.Proveedor) -> Unit,
    Providers: List<Personastodas.Proveedor> = emptyList()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF343341)),
    ) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                Arrangement.Center
            ) {
                Text(
                    text = "Nombre", modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp)
                )
                Text(
                    text = "documento", modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                )
                Text(
                    text = "direccion", modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                )
                Text(
                    text = "email", modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                )
                Text(
                    text = "telefono", modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp)
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .background(Color(android.graphics.Color.parseColor("#737A8C")))
        ) {
            items(Providers) { proveedor ->

            }
        }
    }
}




