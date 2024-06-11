package com.solidtype.atenas_apk_2.servicios

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.MapsUgc
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.Support
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiceEvent
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.ServiciosViewModel
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.ui.theme.Rojo
import java.time.LocalDate

@OptIn(ExperimentalMultiplatform::class, ExperimentalMaterial3Api::class)
@Composable
fun selector(
    viewmodel: ServiciosViewModel = hiltViewModel(), listaSericios: List<servicio>,
    listaCliente: List<Personastodas.ClienteUI?>, listaDispositivos: List<Dispositivo?>

//    nombre: MutableState<String>,
//    modelo: MutableState<String>,
//    telefono: MutableState<String>,
//    email: MutableState<String>,
//    falla: MutableState<String>,
//    estado: MutableState<String>,
//    marca: MutableState<String>,
//    abono: MutableState<String>,
//    nota: MutableState<String>,
//    restante: MutableState<String>,
//    total: MutableState<String>,
//    selectedText: MutableState<String>,
//    dia: MutableState<String>,
//    precio: MutableState<String>,
//    descrp: MutableState<String>,

) {
    val state by viewmodel.uiStates.collectAsStateWithLifecycle()
    val listacliente = state.listaClientes
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
    var abono by rememberSaveable { mutableStateOf("0.0") }
    var nota by rememberSaveable { mutableStateOf("") }
    var restante by rememberSaveable { mutableStateOf("0.0") }
    var total by rememberSaveable { mutableStateOf("0.0") }

    var sub by rememberSaveable { mutableStateOf("0.0") }
    var impuesto by rememberSaveable { mutableStateOf("0.0") }
    var accesorios by rememberSaveable { mutableStateOf("acessiom") }
    var fecha_ini by rememberSaveable { mutableStateOf("20/04/24") }
    var fecha_fin by rememberSaveable { mutableStateOf("20/05/24") }

    val id_vendedor: Long = 1
    val id_cliente: Long = 1
    val id_tipo: Long = 1
    val id_dipo: Long = 1

    //formulario servicio
    val context = LocalContext.current
    val coffeeDrinks: List<servicio?> = listaSericios
    var expanded by remember { mutableStateOf(true) }
    var selectedText by remember { mutableStateOf("") }
    var dia by rememberSaveable { mutableStateOf("0") }
    var precio by rememberSaveable { mutableStateOf("0.0") }
    var descrp by rememberSaveable { mutableStateOf("") }
    val mostrar1 = remember { mutableStateOf(false) }

    //agregar dispositiviso
    var agra_marca by rememberSaveable { mutableStateOf("") }
    var agra_modelo by rememberSaveable { mutableStateOf("") }
    var nom_comercial by rememberSaveable { mutableStateOf("") }

    //selector cliente
    val contex = LocalContext.current
    val coffee: List<Personastodas.ClienteUI?> = listaCliente
    var expande by remember { mutableStateOf(false) }

    //selecto modelo
    val contex1 = LocalContext.current
    val coffee1: List<Dispositivo?> = listaDispositivos
    var expande1 by remember { mutableStateOf(false) }

    //selecto marca
    val contex2 = LocalContext.current
    val coffee2: List<Dispositivo?> = listaDispositivos
    var expande2 by remember { mutableStateOf(false) }

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
//                                cliente(listacliente = state.listaClientes)


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
                                            .background(
                                                GrisOscuro,
                                                shape = RoundedCornerShape(20.dp)
                                            )
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .fillMaxWidth()

                                        ) {
                                            Text(
                                                text = "Cliente",
                                                modifier = Modifier.weight(1f),
                                                color = Blanco,
                                                textAlign = TextAlign.Center,
                                                fontSize = 20.sp,
                                            )
                                            Text(
                                                text = "Telefono",
                                                modifier = Modifier.weight(1f),
                                                color = Blanco,
                                                textAlign = TextAlign.Center,
                                                fontSize = 20.sp,
                                            )
                                            Text(
                                                text = "Accion",
                                                modifier = Modifier.weight(1f),
                                                color = Blanco,
                                                textAlign = TextAlign.Center,
                                                fontSize = 20.sp,
                                            )
                                        }


                                        LazyColumn(
                                            modifier = Modifier
                                                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                                                .fillMaxSize()
                                                .background(GrisOscuro)
                                        ) { //buscar componente para agregar filas de cards
                                            itemsIndexed(listacliente) { i, clientes ->
                                                Column {
                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(40.dp)
                                                            .padding(3.dp)
                                                            .clip(RoundedCornerShape(50.dp))
                                                            .background(Blanco),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            text = clientes?.nombre.toString(),
                                                            modifier = Modifier.weight(0.5f),
                                                            textAlign = TextAlign.Center
                                                        )
                                                        Text(
                                                            text = clientes?.telefono.toString(),
                                                            modifier = Modifier.weight(1f),
                                                            textAlign = TextAlign.Center

                                                        )



                                                        Box(
                                                            modifier = Modifier
                                                                .weight(0.5f)
                                                        ) {

                                                            card(clienteUI = clientes!!) {
                                                                nombre = clientes.nombre.toString()
                                                                telefono = clientes.telefono.toString()
                                                                email = clientes.email.toString()
                                                                openDialog.value = true
                                                                mostrar1.value = false
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
                                                //------------->selector cliente<-----------------
                                                ExposedDropdownMenuBox(
                                                    expanded = expande,
                                                    onExpandedChange = {
                                                        expande = !expande
                                                    }
                                                ) {
                                                    TextField(
                                                        modifier = Modifier
                                                            .width(240.dp)
                                                            .padding(0.dp)
                                                            .menuAnchor()
                                                            .clip(RoundedCornerShape(15.dp)),
                                                        value = nombre,
                                                        onValueChange = { nombre = it },
                                                        label = {
                                                            Text(
                                                                text = "Selecionar cliente",
                                                                color = Color.Black,
                                                                fontSize = 16.sp,
                                                            )
                                                        },
                                                        trailingIcon = {
                                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                                expanded = expande
                                                            )
                                                        },
                                                    )

                                                    val filteredOptions = coffee.filter {
                                                        it?.nombre?.contains(
                                                            nombre,
                                                            ignoreCase = true
                                                        ) == true
                                                    }
                                                    if (filteredOptions.isNotEmpty()) {
                                                        ExposedDropdownMenu(
                                                            expanded = expande,
                                                            onDismissRequest = {
                                                                // We shouldn't hide the menu when the user enters/removes any character
                                                            }
                                                        ) {
                                                            filteredOptions.forEach { item ->
                                                                DropdownMenuItem(text = {
                                                                    item!!.nombre?.let {
                                                                        Text(
                                                                            text = it
                                                                        )
                                                                    }
                                                                },
                                                                    onClick = {
                                                                        nombre = item?.nombre ?: ""
                                                                        telefono = item?.telefono.toString()
                                                                        email = item?.email.toString()
                                                                        expande = false
                                                                        Toast.makeText(
                                                                            contex,
                                                                            item!!.nombre,
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                    }
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            Spacer(modifier = Modifier.padding(20.dp))
                                            Box() {
                                                //------------------seleccion modeo------------------
                                                ExposedDropdownMenuBox(
                                                    expanded = expande1,
                                                    onExpandedChange = {
                                                        expande1 = !expande1
                                                    }
                                                ) {
                                                    TextField(
                                                        modifier = Modifier
                                                            .width(240.dp)
                                                            .padding(0.dp)
                                                            .menuAnchor()
                                                            .clip(RoundedCornerShape(15.dp)),
                                                        value = modelo,
                                                        onValueChange = { modelo = it },
                                                        label = {
                                                            Text(
                                                                text = "Selecionar modelo",
                                                                color = Color.Black,
                                                                fontSize = 16.sp,
                                                            )
                                                        },
                                                        trailingIcon = {
                                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                                expanded = expande1
                                                            )
                                                        },
                                                    )

                                                    val filteredOptions = coffee1.filter {
                                                        it?.modelo?.contains(
                                                            modelo,
                                                            ignoreCase = true
                                                        ) == true
                                                    }
                                                    if (filteredOptions.isNotEmpty()) {
                                                        ExposedDropdownMenu(
                                                            expanded = expande1,
                                                            onDismissRequest = {
                                                                // We shouldn't hide the menu when the user enters/removes any character
                                                            }
                                                        ) {
                                                            filteredOptions.forEach { item ->
                                                                DropdownMenuItem(text = {
                                                                    item!!.modelo.let {
                                                                        Text(
                                                                            text = it
                                                                        )
                                                                    }
                                                                },
                                                                    onClick = {
                                                                        modelo = item?.modelo ?: ""
                                                                        marca = item?.marca.toString()
                                                                        expande1 = false
                                                                        Toast.makeText(
                                                                            contex1,
                                                                            item!!.modelo,
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                    }
                                                                )
                                                            }
                                                        }
                                                    }
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

                                //--------------------------555
                                if (!nota.isNullOrEmpty()) {
                                    viewmodel.onEvent(
                                        ServiceEvent.CrearTicket(
                                            ticket(
                                                id_vendedor = 1,
                                                id_cliente = 1,
                                                id_tipo_venta = 1,
                                                id_dispositivo = 1,
                                                imei = email,
                                                falla = falla,
                                                descripcion = descrp,
                                                nota = nota,
                                                assesorios = accesorios,
                                                total = total.toDouble(),
                                                abono = abono.toDouble(),
                                                presupuesto = precio.toDouble(),
                                                subtotal = sub.toDouble(),
                                                impuesto = impuesto.toDouble(),
                                                fecha_inicio = LocalDate.now(),
                                                fecha_final = LocalDate.now(),
                                                estado = true
                                            )
                                        )
                                    )
                                }


                            }) {
                            if (!mostrar.value) {
                                Text("Siguiente", color = Blanco)
                            } else {

                                Text("Guardar", color = Blanco)

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
                            Spacer(modifier = Modifier.padding(horizontal = 140.dp))
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
    val openDialog2 = remember { mutableStateOf(false) }
    val altura1 = remember { mutableStateOf(300.dp) }

    var nuevoServicio by rememberSaveable { mutableStateOf("") }

    //cuerpo del modal agregar servicio
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
//modal agregar dipositivos
    if (openDialog2.value) {
        Box(
            modifier = Modifier.fillMaxWidth()

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog2.value = false
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
                                    text = "Nuevo Dipositivos",
                                    color = AzulGris,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 35.sp,
                                )
                                Spacer(modifier = Modifier.padding(top = 15.dp))
                                //cuerpo1
                                    Row(){
                                    Box() {
                                        Input(
                                            label = "Marca",
                                            valor = agra_marca,
                                            derecho = true,
                                            modifier = Modifier
                                        ) {
                                            agra_marca = it
                                        }
                                    }
                                      Spacer(modifier = Modifier.padding(start = 30.dp))
                                    Box() {
                                        Input(
                                            label = "Modelo",
                                            valor = agra_modelo,
                                            derecho = true,
                                            modifier = Modifier
                                        ) {
                                            agra_modelo = it
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.padding(top = 10.dp))
                                Box() {
                                    Inputlargo(
                                        label = "Comercial",
                                        valor = nom_comercial,
                                        derecho = true,
                                        modifier = Modifier
                                    ) {
                                        nom_comercial = it
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
                                if (!nom_comercial.isNullOrEmpty()) {
                                    viewmodel.onEvent(
                                        ServiceEvent.CrearDispositivo(
                                            Dispositivo(
                                                nombre_comercial = nom_comercial,
                                                modelo = agra_modelo,
                                                marca = agra_marca,
                                            )
                                        )
                                    )
                                    openDialog2.value = false
//                                    Toast.makeText(
//                                       Text(text = "Guardado Existoxamente")
//                                    )
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
                                openDialog2.value = false
                            },
                        ) {
                            Text("Salir", color = Blanco)
                        }
                    },

                    modifier = Modifier
                        .width(800.dp)
//                        .background(GrisOscuro)
                        .height(400.dp),
                )
            }
        }
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 1160.dp, top = 530.dp)
    ) {

        Icon(imageVector = Icons.Filled.Support,
            contentDescription = "",
            tint = AzulGris,
            modifier = Modifier
                .padding(bottom = 0.dp)
                .size(60.dp)
                .clickable {
                    openDialog2.value = true
                })
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