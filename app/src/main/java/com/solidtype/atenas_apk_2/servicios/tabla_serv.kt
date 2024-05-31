package com.solidtype.atenas_apk_2.servicios

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.facturacion.presentation.Factura
import com.solidtype.atenas_apk_2.facturacion.presentation.sumaTotal
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.TicketVista
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisAzulado
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoParaUser

@Composable
fun tablaserv(listaTiket: List<TicketVista?> = listOf()) {

    var desplegar by rememberSaveable { mutableStateOf(List(servicio.size) { false }) }
    val size = LocalConfiguration.current.screenWidthDp.dp - 750.dp




    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp)
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
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Nombre",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
                Text(
                    text = "Servicios",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
                Text(
                    text = "Subtotal",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
                Text(
                    text = "Estado",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
            }

            if (!listaTiket.isEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .fillMaxSize()
                    .background(GrisOscuro)
            ) { //buscar componente para agregar filas de cards
                itemsIndexed(listaTiket) { i, servicio ->
                    Column {
                        Row(
                            modifier = Modifier
                                .padding(
                                    bottom = when (desplegar[i]) {
                                        true -> 0.dp
                                        false -> 5.dp
                                    }
                                )
                                .fillMaxWidth()
                                .height(40.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(Blanco)
                                .clickable {
                                    desplegar = desplegar
                                        .toMutableList()
                                        .also {
                                            it[i] = !it[i]
                                        }
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = servicio?.numeroFactura.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = servicio?.iDservicio.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = servicio?.subtotal.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = servicio?.Estado.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }


        }
}


}