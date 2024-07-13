package com.solidtype.atenas_apk_2.historial_ventas.presentation.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorialUIState
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.formatoParaUser
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun Tabla(
    selected: MutableState<String>,
    uiState: HistorialUIState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(Pantalla.ancho - 820.dp)
            .background(Blanco, RoundedCornerShape(16.dp))
    ) {
        when (selected.value) {
            "Ventas" -> { //selected == "Ventas"
                //Aquí va el menú de ventas
                Row {
                    Text(
                        "Código",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Cliente",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Fecha",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Subtotal",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Total",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Cantidad",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Estado",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
                Divider()
                LazyColumn {
                    items(uiState.Historial.size) { i ->
                        Row {
//                            Text(
//                                uiState.Historial[i].id_venta.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Historial[i].id_cliente.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Historial[i].fecha.toString().formatoParaUser(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Historial[i].subtotal.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Historial[i].total.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Historial[i].cantidad.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Historial[i].estado.formatoActivo(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
                        }
                        Divider()
                    }
                }
            }

            "Ticket" -> {
                //Aquí va el menú de ticket
                Row {
                    Text(
                        "Código",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Descripción",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Fecha_Ini",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Fecha_Fin",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Subtotal",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Impuesto",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Total",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "Cliente",
                        fontSize = 20.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
                Divider()
                LazyColumn {
                    items(uiState.Ticket.size) { i ->
                        Row {
//                            Text(
//                                uiState.Ticket[i].id_ticket.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Ticket[i].descripcion,
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Ticket[i].fecha_inicio.toString()
//                                    .formatoParaUser(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Ticket[i].fecha_final.toString()
//                                    .formatoParaUser(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Ticket[i].subtotal.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Ticket[i].impuesto.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Ticket[i].total.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
//                            Text(
//                                uiState.Ticket[i].id_cliente.toString(),
//                                fontSize = 16.sp,
//                                modifier = Modifier.weight(1f),
//                                textAlign = TextAlign.Center
//                            )
                        }
                        Divider()
                    }
                }
            }

            else -> {
                Text(text = "Seleccione un tipo de reporte", fontSize = 24.sp)
            }
        }
    }
}