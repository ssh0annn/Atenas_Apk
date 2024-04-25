package com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.facturacion.presentation.Factura
import com.solidtype.atenas_apk_2.facturacion.presentation.sumaTotal
import com.solidtype.atenas_apk_2.util.formatoParaUser

@Composable
fun Tabla(facturas: List<Factura> = listOf()){

    var desplegar by rememberSaveable { mutableStateOf(List(facturas.size) { false }) }
    val size = LocalConfiguration.current.screenWidthDp.dp - 780.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(size)
            .background(Color(0xFF343341), shape = RoundedCornerShape(20.dp))
    ) { // Datos o Tabla
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .background(Color(0xFF737A8C), shape = RoundedCornerShape(20.dp))
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    text = "No. Factura",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Fecha",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Total",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Tipo de Pago",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "ID",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center
                )
            }
            LazyColumn(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .fillMaxSize()
                    .background(Color(0xFF737A8C))
            ) { //buscar componente para agregar filas de cards
                itemsIndexed(facturas) { i, factura ->
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
                                .background(Color(0xFFFFFFFF))
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
                                text = factura.noFactura.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = factura.fecha.formatoParaUser(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = factura.total.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = factura.tipoPago,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = factura.idVendedor.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }
                        AnimatedVisibility(desplegar[i]) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                                    .background(Color(0xFF8285A5))
                            ) {
                                Column {
                                    Row {//Cabecera
                                        Text(
                                            text = "Cantidad",
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Nombre",
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Precio",
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "ITB",
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "Total",
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Divider()
                                    factura.productos.forEach {Producto -> //Cuerpo
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        ) {
                                            Text(
                                                text = Producto.cantidad.toString(),
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                            Text(
                                                text = Producto.nombre,
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                            Text(
                                                text = Producto.precio.toString(),
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                            Text(
                                                text = Producto.ITB.toString(),
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                            Text(
                                                text = Producto.total.toString(),
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                    Divider()
                                    Row{ // Pie
                                        Text(
                                            text = "Total",
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        )
                                        for (@Suppress("NAME_SHADOWING") i in 0..2) {
                                            Text(
                                                text = "",
                                                modifier = Modifier.weight(1f)
                                            )
                                        }
                                        Text(
                                            text = factura.productos.sumaTotal().toString(),
                                            modifier = Modifier.weight(1f),
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}