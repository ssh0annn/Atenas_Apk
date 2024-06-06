package com.solidtype.atenas_apk_2.servicios

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiceEvent
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiciosViewModel
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.ui.theme.Rojo

@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable
fun selector(
    viewmodel: ServiciosViewModel = hiltViewModel(), listaSericios: List<servicio>,
) {
    val state by viewmodel.uiStates.collectAsStateWithLifecycle()
    //modal
    val openDialog = remember { mutableStateOf(false) }
    val mostrar = remember { mutableStateOf(false) }
    val altura = remember { mutableStateOf(400.dp) }


    //formulario cliente
    var nombre by rememberSaveable { mutableStateOf("") }
    var modelo by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var falla by rememberSaveable { mutableStateOf("") }
    var estado by rememberSaveable { mutableStateOf("") }
    var marca by rememberSaveable { mutableStateOf("") }
    var abono by rememberSaveable { mutableStateOf("") }
    var nota by rememberSaveable { mutableStateOf("") }
    var restante by rememberSaveable { mutableStateOf("") }
    var total by rememberSaveable { mutableStateOf("") }

    //formulario servicio
    val context = LocalContext.current
    val coffeeDrinks:List<servicio?> = listaSericios
    var expanded by remember { mutableStateOf(true) }
    var selectedText by remember { mutableStateOf("") }
    var dia by rememberSaveable { mutableStateOf("") }
    var precio by rememberSaveable { mutableStateOf("") }
    var descrp by rememberSaveable { mutableStateOf("") }
    val mostrar1 = remember { mutableStateOf(false) }


    //modal cliente existente

    if (mostrar1.value) {
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        mostrar1.value = false
                    },
                    text = {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                //titulo
                                Spacer(modifier = Modifier.padding(top = 15.dp))
                                Text(
                                    text = "Cliente existentes",
                                    color = AzulGris,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 35.sp,
                                )

                                //cuerpo-------------------------------------
                                cliente(listacliente = state.listaClientes)

                            }
                        }
                    },

                    confirmButton = {
                        TextButton(modifier = Modifier
                            .background(
                                AzulGris, shape = RoundedCornerShape(20.dp)
                            )
                            .padding(5.dp),

                            onClick = {


                            }) {
                            Text("Guardar", color = Blanco)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            modifier = Modifier
                                .background(Rojo, shape = RoundedCornerShape(20.dp))
                                .padding(5.dp),
                            onClick = {
                                mostrar1.value = false
                                openDialog.value = true
                            },
                        ) {
                            Text("Atras", color = Blanco)
                        }
                    },

                    modifier = Modifier
                        .width(800.dp)
//                        .background(GrisOscuro)
                )
            }
        }
    }



    //cuerpo del modal
    if (openDialog.value) {
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    text = {


                        Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
                            if (!mostrar.value) {
                                //seleccionar servicio  <----
                                Column() {
                                    Row() {
                                        Box(
                                            modifier = Modifier
                                                .width(220.dp)
                                                .padding(0.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                        ) {
                                            ExposedDropdownMenuBox(expanded = expanded,
                                                onExpandedChange = {
                                                    expanded = !expanded



                                                }) {
                                                TextField(
                                                    value = selectedText,
                                                    onValueChange = {},
                                                    readOnly = true,
                                                    trailingIcon = {
                                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                                            expanded = expanded
                                                        )
                                                    },
                                                    modifier = Modifier.menuAnchor()
                                                )

                                                ExposedDropdownMenu(modifier = Modifier.height(300.dp),
                                                    expanded = expanded,
                                                    onDismissRequest = { expanded = false }) {
                                                    coffeeDrinks.forEach { item ->
                                                        DropdownMenuItem(text = { Text(text = item!!.nombre) },
                                                            onClick = {
                                                                selectedText = item?.nombre ?: ""
                                                                expanded = false
                                                                Toast.makeText(
                                                                    context,
                                                                    item!!.nombre,
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            })
                                                    }
                                                }
                                            }
                                        }

                                        Column(
                                            modifier = Modifier
                                                .padding(top = 30.dp)
                                                .fillMaxWidth()
                                                .verticalScroll(rememberScrollState()),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {

                                            Text(
                                                text = "Detalles",
                                                color = AzulGris,
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 30.sp,

                                                )
                                            Spacer(modifier = Modifier.padding(top = 10.dp))
                                            Box() {
                                                Inputmed(
                                                    label = "Servicio",
                                                    valor = selectedText,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {}
                                            }

                                            Row {
                                                Box {
                                                    Inputpeq(
                                                        label = "Dias", valor = dia,
//                                        derecho = true,
                                                        modifier = Modifier
                                                    ) {
                                                        dia = it
                                                    }
                                                }
                                                Box {
                                                    Inputt(
                                                        label = "Precio", valor = precio,
//                                        derecho = true,
                                                        modifier = Modifier
                                                    ) {
                                                        precio = it
                                                    }
                                                }
                                            }
                                            Box() {
                                                Inputmed(
                                                    label = "Descripcion",
                                                    valor = descrp,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    descrp = it
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.padding(top = 20.dp))

                                    }
                                }
                                altura.value = 400.dp
                            } else {

                                //formulario cliente  <---
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        //titulo
                                        Spacer(modifier = Modifier.padding(top = 15.dp))
                                        Text(
                                            text = "Cliente",
                                            color = AzulGris,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 35.sp,
                                        )

                                        //cuerpo1
                                        Row(
                                            modifier = Modifier.padding(top = 25.dp)
                                        ) {

                                            Box() {
                                                Input(
                                                    label = "Nombre",
                                                    valor = nombre,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    nombre = it
                                                }
                                            }
                                            Spacer(modifier = Modifier.padding(20.dp))
                                            Box() {
                                                Input(
                                                    label = "Modelo",
                                                    valor = modelo,
                                                    derecho = true,
                                                    modifier = Modifier

                                                ) {
                                                    modelo = it
                                                }
                                            }
                                        }
                                        //cuerpo2
                                        Row(
                                            modifier = Modifier.padding(top = 10.dp)
                                        ) {

                                            Box() {
                                                Input(
                                                    label = "Telefono",
                                                    valor = telefono,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    telefono = it
                                                }
                                            }
                                            Spacer(modifier = Modifier.padding(20.dp))
                                            Box() {
                                                Input(
                                                    label = "Email",
                                                    valor = email,
                                                    derecho = true,
                                                    modifier = Modifier

                                                ) {
                                                    email = it
                                                }
                                            }
                                        }
                                        //cuerpo3
                                        Row(
                                            modifier = Modifier.padding(top = 0.dp)
                                        ) {

                                            Box() {
                                                Inputlargo(
                                                    label = "Falla del equipo",
                                                    valor = falla,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    falla = it
                                                }
                                            }
                                        }
                                        //cuerpo4
                                        Row(
                                            modifier = Modifier.padding()
                                        ) {

                                            Box() {
                                                Inputlargo(
                                                    label = "Estado del equipo",
                                                    valor = estado,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    estado = it
                                                }
                                            }
                                        }
                                        //cuerpo5
                                        Row(
                                            modifier = Modifier.padding(top = 0.dp)
                                        ) {

                                            Box() {
                                                Input(
                                                    label = "Marca",
                                                    valor = marca,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    marca = it
                                                }
                                            }
                                            Spacer(modifier = Modifier.padding(20.dp))
                                            Box() {
                                                Input(
                                                    label = "Abono",
                                                    valor = abono,
                                                    derecho = true,
                                                    modifier = Modifier

                                                ) {
                                                    abono = it
                                                }
                                            }
                                        }
                                        //cuerpo6
                                        Row(
                                            modifier = Modifier.padding()
                                        ) {

                                            Box() {
                                                Inputlargo(
                                                    label = "Nota",
                                                    valor = nota,
                                                    derecho = true,
                                                    modifier = Modifier.padding(top = 50.dp)
                                                ) {
                                                    nota = it
                                                }
                                            }
                                        }
                                        //cuerpo7
                                        Row(
                                            modifier = Modifier.padding(top = 0.dp)
                                        ) {

                                            Box() {
                                                Input(
                                                    label = "Restante",
                                                    valor = restante,
                                                    derecho = true,
                                                    modifier = Modifier
                                                ) {
                                                    restante = it
                                                }
                                            }
                                            Spacer(modifier = Modifier.padding(20.dp))
                                            Box() {
                                                Input(
                                                    label = "Total",
                                                    valor = total,
                                                    derecho = true,
                                                    modifier = Modifier

                                                ) {
                                                    total = it
                                                }
                                            }
                                        }
                                    }
                                }
                                altura.value = 600.dp
                            }
                        }
                    },



                    confirmButton = {
                        TextButton(modifier = Modifier
                            .background(
                                AzulGris, shape = RoundedCornerShape(20.dp)
                            )
                            .padding(5.dp),

                            onClick = {
                                mostrar.value = true
                            }) {
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
                        // boton cliente------------------------------
                        if (!mostrar.value) {
                        } else {
                            Spacer(modifier = Modifier .padding(horizontal = 140.dp))
                            Icon(imageVector = Icons.Filled.SupervisedUserCircle,
                                contentDescription = "",
                                tint = AzulGris,
                                modifier = Modifier
                                    .padding(bottom = 0.dp)
                                    .size(60.dp)
                                    .clickable {
                                        viewmodel.onEvent(ServiceEvent.GetClientes)
                                        openDialog.value = false
                                        mostrar1.value = true
                                    })
                        }
                    },

                    modifier = Modifier
                        .width(800.dp)
//                        .background(GrisOscuro)
                        .height(altura.value),
                )
            }
        }
    }

    //modal
    val openDialog1 = remember { mutableStateOf(false) }
//    val mostrar1 = remember { mutableStateOf(false) }
    val altura1 = remember { mutableStateOf(300.dp) }

    var nuevoServicio by rememberSaveable { mutableStateOf("") }

    //cuerpo del modal
    if (openDialog1.value) {
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog1.value = false
                    },
                    text = {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                //titulo
                                Spacer(modifier = Modifier.padding(top = 15.dp))
                                Text(
                                    text = "Nuevo Servicio",
                                    color = AzulGris,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 35.sp,
                                )

                                //cuerpo1
                                Row(
                                    modifier = Modifier.padding(top = 25.dp)
                                ) {

                                    Box() {
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
                        TextButton(modifier = Modifier
                            .background(
                                AzulGris, shape = RoundedCornerShape(20.dp)
                            )
                            .padding(5.dp),

                            onClick = {
                                if (!nuevoServicio.isNullOrEmpty()) {
                                    viewmodel.onEvent(
                                        ServiceEvent.CreateServicio(
                                            servicio(
                                                nombre = nuevoServicio,
                                                descripcion = nuevoServicio,
                                                estado = true
                                            )
                                        )
                                    )
                                    openDialog1.value = false
                                }

                            }) {
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
//                        .background(GrisOscuro)
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

        Icon(imageVector = Icons.Filled.SupervisedUserCircle,
            contentDescription = "",
            tint = AzulGris,
            modifier = Modifier
                .padding(bottom = 0.dp)
                .size(60.dp)
                .clickable {
                    openDialog1.value = true
                })
        Icon(imageVector = Icons.Filled.AddCircle,
            contentDescription = "",
            tint = AzulGris,
            modifier = Modifier
                .padding(top = 0.dp)
                .size(60.dp)
                //abrir modal
                .clickable {
                    openDialog.value = true
                })
    }
}