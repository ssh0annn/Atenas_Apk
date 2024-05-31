package com.solidtype.atenas_apk_2.servicios

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.Authentication.presentation.register.onClick
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisAzulado
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.ui.theme.PurpleGrey80
import com.solidtype.atenas_apk_2.ui.theme.Rojo

@Composable
fun selector() {

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

                        Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
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
                        ) {
                            if (!mostrar.value) {
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
                        Box(modifier = Modifier) {
                            cuerpo3()
                        }
                    },

                    confirmButton = {
                        TextButton(
                            modifier = Modifier
                                .background(AzulGris, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),

                            onClick = {
                                mostrar1.value = true
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