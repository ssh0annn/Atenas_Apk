package com.solidtype.atenas_apk_2.servicios.presentation.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.TicketwithRelation
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorialUIState
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.TicketVista
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.BlancoOpaco
import com.solidtype.atenas_apk_2.ui.theme.GrisAzulado
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.formatoParaUser
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun tablatick(listaT: List<TicketwithRelation> = listOf()) {

    val scrollState = rememberScrollState()
    val ancho = 200.dp


Box(
modifier = Modifier
    .fillMaxWidth()
    .height(310.dp)
    .background(AzulGris, shape = RoundedCornerShape(20.dp))
) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(GrisOscuro, shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center

    ) {

        LazyRow(
            modifier = Modifier
                .padding(start = 10.dp, end = 20.dp)
                .fillMaxSize()
                .background(GrisOscuro)
        ) {
            itemsIndexed(listaT) { i, ticket ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(360.dp)
                            .padding(start = 20.dp, top = 25.dp)
                            .background(AzulGris, shape = RoundedCornerShape(20.dp)),

                ) {
                    Spacer(modifier = Modifier.padding(top = 30.dp))
                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
                            .padding(15.dp)
                            .background(GrisAzulado)

                    ) {
                        Column(
                            modifier = Modifier.padding(15.dp)
                        ) {
                            Row{
                                Text(text = "Nfactura:", fontSize = 20.sp, color = BlancoOpaco)
                                Spacer(modifier = Modifier.padding(start = 10.dp))
                                Text(text = ticket?.ticket?.id_tipo_venta.toString(),fontSize = 20.sp, color = AzulGris)
                            }
                            Row {
                                Text(text = "Nombre:", fontSize = 20.sp, color = Blanco)
                                Spacer(modifier = Modifier.padding(start = 10.dp))
                                ticket?.cliente?.nombre?.let { nombre ->
                                    Text(text = nombre, fontSize = 20.sp, color = Blanco)
                                    Spacer(modifier = Modifier.padding(top = 5.dp))
                                }
                            }
                            Row{
                                Text(text = "Servicio:", fontSize = 20.sp, color = Blanco)
                                Spacer(modifier = Modifier.padding(start = 10.dp))
                                ticket?.servicio?.nombre?.let { servicio ->
                                    Text(text = servicio, fontSize = 20.sp, color = Blanco)
                                }
                            }

                            Spacer(modifier = Modifier.padding(top = 20.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .background(Blanco)
                            ) {
                                Column(
                                    modifier = Modifier.padding(top = 10.dp, start = 10.dp)
                                ) {
                                    Text(text = "Nota:")
                                    ticket?.ticket?.nota?.let { nota ->
                                        Text(text = nota)
                                    }
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ticket?.ticket?.estado?.let { estado ->
                                    if (estado) {
                                        Text(
                                            text = "Abierto",
                                            fontSize = 22.sp,
                                            color = Color.Green,
                                            fontWeight = FontWeight.W500
                                        )
                                    } else {
                                        Text(
                                            text = "Cerrado",
                                            fontSize = 22.sp,
                                            color = Color.Red
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


//
//@Composable
//fun cardT(){
//    Box (
//        modifier = Modifier
//    ){
//        Column (
//            modifier = Modifier
//        ){
//            Row {
//                Text(text = "nombre")
//
//            }
//
//        }
//    }
//}
//
//