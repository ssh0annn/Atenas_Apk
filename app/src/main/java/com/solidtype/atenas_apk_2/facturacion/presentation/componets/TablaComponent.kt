package com.solidtype.atenas_apk_2.facturacion.presentation.componets

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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisAzulado
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.formatoParaUser
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun Tabla(facturas: List<FacturaConDetalle?>) {

    var desplegar by rememberSaveable { mutableStateOf(List(facturas.size) { false }) }

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
                    .fillMaxWidth()

            ) {
                Text(
                    text = "No. Factura",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Cliente",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Fecha",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Cantidad",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Total",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Estado",
                    modifier = Modifier.weight(1f),
                    color = Blanco,
                    textAlign = TextAlign.Center
                )
                Text(
                    "",
                    modifier = Modifier.weight(0.2f)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .fillMaxSize()
                    .background(GrisOscuro)
            ) {
                if (facturas.isNotEmpty())
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
                                    text = factura?.factura?.id_venta.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = factura?.factura?.id_cliente.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                factura?.factura?.fecha?.let {
                                    Text(
                                        text = it.formatoParaUser(),
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Text(
                                    text = factura?.factura?.cantidad.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = factura?.detalle?.sumOf { it.total }.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                factura?.factura?.estado?.let {
                                    Text(
                                        text = it.formatoActivo(),
                                        modifier = Modifier.weight(1f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Icon(
                                    imageVector = if (desplegar[i]) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                                    contentDescription = "",
                                    tint = AzulGris,
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .size(20.dp)
                                        .weight(0.2f)
                                )
                            }
                            AnimatedVisibility(desplegar[i]) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                                        .background(GrisAzulado)
                                ) {
                                    Column {
                                        Row {//Cabecera
                                            Text(
                                                text = "id_detalle_venta",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "id_venta",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "id_producto",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "cantidad",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "total",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Divider()
                                        Column {
                                            factura?.detalle?.forEach { detalle ->
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                ) {
                                                    Text(
                                                        text = detalle.id_detalle_venta.toString(),
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center
                                                    )
                                                    Text(
                                                        text = detalle.id_venta.toString(),
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center
                                                    )
                                                    Text(
                                                        text = detalle.id_producto.toString(),
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center
                                                    )
                                                    Text(
                                                        text = detalle.cantidad.toString(),
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center
                                                    )
                                                    Text(
                                                        text = detalle.total.toString(),
                                                        modifier = Modifier.weight(1f),
                                                        textAlign = TextAlign.Center
                                                    )
                                                }
                                            }
                                        }
                                        Divider()
                                        Row { // Pie
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
                                            if (factura != null) {
                                                Text(
                                                    text = factura.detalle.sumOf { it.total }.toString(),
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
}