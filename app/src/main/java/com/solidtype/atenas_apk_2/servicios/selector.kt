package com.solidtype.atenas_apk_2.servicios

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.Authentication.presentation.register.onClick
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiceEvent
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiciosViewModel
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisAzulado
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.ui.theme.PurpleGrey80
import com.solidtype.atenas_apk_2.ui.theme.Rojo

@OptIn(ExperimentalMultiplatform::class)
@Composable
fun selector(
  viewmodel: ServiciosViewModel
) {

    //modal
    val openDialog = remember { mutableStateOf(false) }
    val mostrar = remember { mutableStateOf(false) }
    val altura = remember { mutableStateOf(400.dp) }

    //cuerpo del modal
    if (openDialog.value) {
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    text = {


                Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))){
                    if (!mostrar.value) {
                        cuerpo2()
                        altura.value = 400.dp
                    } else {
                        cuerpo()
                        altura.value = 600.dp
                    }
                }
           },

            confirmButton = {
                TextButton(
                    modifier = Modifier
                        .background(AzulGris, shape = RoundedCornerShape(20.dp))
                        .padding(5.dp),

                    onClick = {
                        mostrar.value = true
                    }
                )  {   if (!mostrar.value) {
                    Text("Siguiente", color = Blanco)
                } else {
                    Text("Acceptar", color = Blanco)
                }

                }
            },
                    dismissButton = {
                        TextButton(
                            modifier = Modifier
                                .background(Rojo, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),
                            onClick = {
                                if (!mostrar.value) {
                                    openDialog.value = false
                                } else {
                                    mostrar.value = false
                                }
//                        openDialog.value = false
//                        mostrar.value = false
                            },
                        ) {
                            if (!mostrar.value) {
                                Text("Salir", color = Blanco)
                            } else {
                                Text("Atras", color = Blanco)

                            }

                        }
                    },

                    modifier = Modifier
                        .width(800.dp)
                        .background(GrisOscuro)
                        .height(altura.value),
                )
            }
        }
    }

    //modal
    val openDialog1 = remember { mutableStateOf(false) }
    val mostrar1 = remember { mutableStateOf(false) }
    val altura1 = remember { mutableStateOf(300.dp) }

    var nuevoServicio by rememberSaveable { mutableStateOf("") }

    //cuerpo del modal
    if (openDialog1.value) {
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog1.value = false
                    },
                    text = {

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())){
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                //titulo
                                Spacer(modifier = Modifier .padding(top = 15.dp))
                                Text(
                                    text = "Nuevo Servicio",
                                    color = AzulGris,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 35.sp,
                                )

                                //cuerpo1
                                Row (
                                    modifier = Modifier
                                        .padding(top = 25.dp)
                                ){

                                    Box (){
                                        Input(
                                            label = "Servico",
                                            valor = nuevoServicio,
                                            derecho = true,
                                            modifier = Modifier
                                        ) {
                                            nuevoServicio = it
                                        }
                                    }
                                }
                            }
                        }
                    },

                    confirmButton = {
                        TextButton(
                            modifier = Modifier
                                .background(AzulGris, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),

                            onClick = {
                                if(!nuevoServicio.isNullOrEmpty()){
                                    viewmodel.onEvent(ServiceEvent.CreateServicio(
                                        servicio(
                                            nombre = nuevoServicio,
                                            descripcion = nuevoServicio,
                                            estado = true
                                        )
                                    ))
                                    openDialog1.value = false
                                }

                            }
                        ) {
                            Text("Guardar", color = Blanco)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            modifier = Modifier
                                .background(Rojo, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),
                            onClick = {
                                openDialog1.value = false
                            },
                        ) {
                            Text("Salir", color = Blanco)
                        }
                    },

                    modifier = Modifier
                        .width(800.dp)
                        .background(GrisOscuro)
                        .height(altura1.value),
                )
            }
        }
    }



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 1200.dp, top = 530.dp)
    ) {

        Icon(
            imageVector = Icons.Filled.SupervisedUserCircle,
            contentDescription = "",
            tint = AzulGris,
            modifier = Modifier
                .padding(bottom = 0.dp)
                .size(60.dp)
                .clickable {
                    openDialog1.value = true
                }
        )
        Icon(
            imageVector = Icons.Filled.AddCircle,
            contentDescription = "",
            tint = AzulGris,
            modifier = Modifier
                .padding(top = 0.dp)
                .size(60.dp)
                //abrir modal
                .clickable {
                    openDialog.value = true
                }
        )
    }
}