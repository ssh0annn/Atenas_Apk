package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor

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
import androidx.compose.material.icons.filled.LocalShipping
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
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor.componets.TableProviders
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
fun ProveedorScreen(
    navController: NavController,
    viewModel: ProveedorViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val mostrarDialogo = rememberSaveable { mutableStateOf(false) }
    val editar = rememberSaveable { mutableStateOf(false) }

    //formulario proveedor
    val idProveedor = rememberSaveable { mutableStateOf("") }
    val nombre = rememberSaveable { mutableStateOf("") }
    val tipoDocumento = rememberSaveable { mutableStateOf("") }
    val numDocumento = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val telefono = rememberSaveable { mutableStateOf("") }
    val direccion = rememberSaveable { mutableStateOf("") }

    //LISTA SELECTOR
    val documetSelector = listOf("Cédula", "Pasaporte")
    //estado busqueda
    val busqueda = rememberSaveable { mutableStateOf("") }

    val mostrarConfirmar = rememberSaveable { mutableStateOf(false) }

    if (uiState.mensaje.isNotEmpty()) {
        Toast.makeText(context, uiState.mensaje, Toast.LENGTH_LONG).show()
        viewModel.onUserEvent(ProveedorEvent.LimpiarMensaje)
    }

    if (busqueda.value.isNotBlank()) {
        viewModel.onUserEvent(ProveedorEvent.BuscarProveedor(busqueda.value))
    } else {
        viewModel.onUserEvent(ProveedorEvent.MostrarProveedorEvent)
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
                    Titulo("Proveedor", Icons.Default.LocalShipping)
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Buscador(busqueda = busqueda.value) {
                            busqueda.value = it
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    TableProviders(
                        uiState.proveedores,
                        mostrarDialogo,
                        editar,
                        nombre,
                        numDocumento,
                        email,
                        telefono,
                        mostrarConfirmar,
                        idProveedor
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Boton("Agregar") {
                            mostrarDialogo.value = true
                            editar.value = false

                            idProveedor.value = ""
                            nombre.value = ""
                            direccion.value = ""
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
            titulo = if (editar.value) "Editar Proveedor" else "Nuevo Proveedor",
            mostrar = mostrarDialogo.value,
            onCerrarDialogo = { mostrarDialogo.value = false },
            clickable = true,
            sinBoton = true
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
                        items = documetSelector,
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
                    InputDetalle(
                        label = "Dirección",
                        valor = direccion.value,
                    ) {
                        direccion.value = it
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
                        telefono.value.matches("8\\d9\\d{7}".toRegex()) &&
                        direccion.value.isNotEmpty()
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
                                ProveedorEvent.EditarProveedor(
                                    Personastodas.Proveedor(
                                        idProveedor.value.toLong(),
                                        nombre.value,
                                        tipoDocumento.value,
                                        numDocumento.value,
                                        direccion.value,
                                        telefono.value,
                                        email.value,
                                    )
                                )
                            )
                            mostrarDialogo.value = false
                            Toast.makeText(
                                context,
                                "El proveedor fue editado con éxito",
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                else
                    Boton("Agregar", camposCompletos) {
                        try {
                            if (!camposCompletos) {
                                throw Exception("Campos vacios.")
                            }
                            viewModel.onUserEvent(
                                ProveedorEvent.AgregarProveedor(
                                    Personastodas.Proveedor(
                                        0,
                                        nombre.value,
                                        tipoDocumento.value,
                                        numDocumento.value,
                                        direccion.value,
                                        telefono.value,
                                        email.value,
                                    )
                                )
                            )

                            nombre.value = ""
                            tipoDocumento.value = ""
                            numDocumento.value = ""
                            direccion.value = ""
                            email.value = ""
                            telefono.value = ""
                        } catch (e: Exception) {
                            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                        }
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
                    text = "¿Estás seguro que deseas eliminar este proveedor?",
                    textAlign = TextAlign.Center,
                    color = AzulGris,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Boton("Aceptar") {
                        try {
                            mostrarConfirmar.value = false

                            viewModel.onUserEvent(
                                ProveedorEvent.BorrarProveedor(
                                    Personastodas.Proveedor(
                                        idProveedor.value.toLong(),
                                        nombre.value,
                                        tipoDocumento.value,
                                        numDocumento.value,
                                        direccion.value,
                                        telefono.value,
                                        email.value,
                                    )
                                )
                            )
                            Toast.makeText(
                                context,
                                "Se eliminó el proveedor",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "No se pudo eliminar",
                                Toast.LENGTH_LONG
                            ).show()
                        }
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