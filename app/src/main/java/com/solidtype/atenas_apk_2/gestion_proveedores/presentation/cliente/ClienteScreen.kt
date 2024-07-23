package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente

import android.annotation.SuppressLint
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.FactCheck
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.TipoUserSingleton
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets.TableClients
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.util.ui.components.SwitchInactivos
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Buscador
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.components.InputDetalle
import com.solidtype.atenas_apk_2.util.ui.components.Loading
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.components.Titulo

@OptIn(ExperimentalMultiplatform::class)
@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@Composable
fun ClienteScreen(
    navController: NavController,
    viewModel: ClientesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val mostrarDialogo = rememberSaveable { mutableStateOf(false) }
    val editar = rememberSaveable { mutableStateOf(false) }

    //formulario cliente
    val idCliente = rememberSaveable { mutableStateOf("") }
    val nombre = rememberSaveable { mutableStateOf("") }
    val tipoDocumento = rememberSaveable { mutableStateOf("") }
    val numDocumento = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val telefono = rememberSaveable { mutableStateOf("") }

    //estado busqueda
    val busqueda = rememberSaveable { mutableStateOf("") }

    val mostrarConfirmar = rememberSaveable { mutableStateOf(false) }

    if (uiState.mensaje.isNotEmpty()) {
        Toast.makeText(context, uiState.mensaje, Toast.LENGTH_LONG).show()
        viewModel.onUserEvent(ClienteEvent.LimpiarMensaje)
    }

    if (busqueda.value.isNotBlank()) {
        viewModel.onUserEvent(ClienteEvent.BuscarClientes(busqueda.value))
    } else {
        viewModel.onUserEvent(ClienteEvent.MostrarClientesEvent)
    }

    if (TipoUserSingleton.tipoUser != TipoUser.ADMIN) {
        navController.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Loading(true)
        }
    } else {
        Column(
            //All
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(GrisClaro),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 50.dp, vertical = 25.dp)
            ) {//Contenedor
                Column {
                    Titulo("Clientes", Icons.AutoMirrored.Outlined.FactCheck)
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Box(modifier = Modifier.weight(3f)) {
                            Buscador(busqueda.value) { busqueda.value = it }
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            SwitchInactivos(uiState.switch) {
                                viewModel.onUserEvent(ClienteEvent.Switch)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    TableClients(
                        uiState.clientes,
                        mostrarDialogo,
                        editar,
                        nombre,
                        tipoDocumento,
                        numDocumento,
                        email,
                        telefono,
                        mostrarConfirmar,
                        idCliente,
                        uiState
                    )

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Boton("Agregar") {
                            mostrarDialogo.value = true
                            editar.value = false

                            idCliente.value = ""
                            nombre.value = ""
                            tipoDocumento.value = ""
                            numDocumento.value = ""
                            telefono.value = ""
                            email.value = ""
                            tipoDocumento.value = ""

                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
        Dialogo(
            max = false,
            titulo = if (editar.value) "Editar Cliente" else "Nuevo Cliente",
            mostrar = mostrarDialogo.value,
            onCerrarDialogo = { mostrarDialogo.value = false },
            clickable = true,
            sinBoton = true,
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFFEEEEEE), RoundedCornerShape(20.dp))
                    .width(400.dp)
                    .height(300.dp)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //cuerpo1
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    InputDetalle(
                        label = "Nombre",
                        valor = nombre.value,
                    ) {
                        nombre.value = it
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    AutocompleteSelect(
                        text = "Tipo de Documento",
                        variableStr = tipoDocumento.value,
                        items = listOf("Cédula", "Pasaporte", "RNC")
                    ) {
                        tipoDocumento.value = it
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    InputDetalle(
                        label = "Numero de documento",
                        valor = numDocumento.value,
                        tipo = KeyboardType.Number
                    ) {
                        numDocumento.value = it
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    InputDetalle(
                        label = "Email",
                        valor = email.value,
                        tipo = KeyboardType.Email
                    ) {
                        email.value = it
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    InputDetalle(
                        label = "Telefono",
                        valor = telefono.value,
                        tipo = KeyboardType.Phone
                    ) {
                        telefono.value = it
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .width(400.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                val camposCompletos =
                        nombre.value.isNotEmpty() &&
                        tipoDocumento.value.isNotEmpty() &&
                        numDocumento.value.matches("[0-9]+".toRegex()) &&
                        Patterns.EMAIL_ADDRESS.matcher(email.value).matches() &&
                        telefono.value.matches("8\\d9\\d{7}".toRegex())
                if (editar.value)
                    Boton(
                        "Editar",
                        camposCompletos
                    ) {
                        try {
                            if (!camposCompletos) {
                                throw Exception("Campos vacios.")
                            }
                            viewModel.onUserEvent(
                                ClienteEvent.EditarClientes(
                                    Personastodas.ClienteUI(
                                        idCliente.value.toLong(),
                                        nombre.value,
                                        tipoDocumento.value,
                                        numDocumento.value,
                                        email.value,
                                        telefono.value
                                    )
                                )
                            )
                            mostrarDialogo.value = false
                        } catch (_: Exception) { }
                    }
                else
                    Boton("Agregar", camposCompletos) {
                        try {
                            if (!camposCompletos) {
                                throw Exception("Campos vacios.")
                            }
                            viewModel.onUserEvent(
                                ClienteEvent.AgregarClientes(
                                    Personastodas.ClienteUI(
                                        id_cliente = 0,
                                        nombre = nombre.value,
                                        tipo_documento = tipoDocumento.value,
                                        documento = numDocumento.value,
                                        telefono = telefono.value,
                                        email = email.value//OJO Estabas pasando el telefono donde iva el emial
                                    )
                                )
                            )
                            nombre.value = ""
                            tipoDocumento.value = ""
                            numDocumento.value = ""
                            email.value = ""
                            telefono.value = ""
                        } catch (_: Exception) { }
                    }

                Spacer(modifier = Modifier.width(20.dp))

                Boton("Cancelar") {
                    mostrarDialogo.value = false
                }
            }
        }
        Dialogo(
            titulo = "Confirma",
            mostrar = mostrarConfirmar.value,
            onCerrarDialogo = { mostrarConfirmar.value = false },
            max = false,
            sinBoton = true
        ) {
            Column(
                modifier = Modifier
                    .width(400.dp)
                    .padding(16.dp, 16.dp, 16.dp, 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿Estás seguro que deseas eliminar este cliente?",
                    textAlign = TextAlign.Center,
                    color = AzulGris,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Boton("Aceptar") {
                        try {
                            if (uiState.switch) {
                                viewModel.onUserEvent(
                                    ClienteEvent.RestaurarClientes/*(
                                        Personastodas.ClienteUI(
                                            idCliente.value.toLong(),
                                            nombre.value,
                                            tipoDocumento.value,
                                            numDocumento.value,
                                            email.value,
                                            telefono.value
                                        )
                                    )*/
                                )
                            } else {
                                viewModel.onUserEvent(
                                    ClienteEvent.BorrarClientes(
                                        Personastodas.ClienteUI(
                                            idCliente.value.toLong(),
                                            nombre.value,
                                            tipoDocumento.value,
                                            numDocumento.value,
                                            email.value,
                                            telefono.value
                                        )
                                    )
                                )
                            }
                            mostrarConfirmar.value = false
                        } catch (_: Exception) { }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Boton("Cancelar") {
                        mostrarConfirmar.value = false
                    }
                }
            }
        }
        MenuLateral(navController)
    }
}