package com.solidtype.atenas_apk_2.gestion_usuarios.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.TipoUserSingleton
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.AreaUsuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.Botones
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoQR
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoRol
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoUsuario
import com.solidtype.atenas_apk_2.util.ui.components.SwitchInactivos
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
import com.solidtype.atenas_apk_2.util.ui.components.Buscador
import com.solidtype.atenas_apk_2.util.ui.components.DialogoConfirmacion
import com.solidtype.atenas_apk_2.util.ui.components.Loading
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.components.Titulo
import com.solidtype.atenas_apk_2.util.ui.components.confirmar

@Composable
fun GestionUsuariosScreen(
    navController: NavController,
    viewModel: UsuariosViewmodel = hiltViewModel()
) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val mostrarDialogo = remember { mutableStateOf(false) }
    val confirmarMensaje = remember { mutableStateOf("") }
    val accionDeConfirmacion = remember { mutableStateOf({}) }

    val busqueda = rememberSaveable { mutableStateOf("") }

    val rol = rememberSaveable { mutableStateOf("") }
    val idUsuario = rememberSaveable { mutableStateOf("") }
    val nombre = rememberSaveable { mutableStateOf("") }
    val apellido = rememberSaveable { mutableStateOf("") }
    val correo = rememberSaveable { mutableStateOf("") }
    val clave = rememberSaveable { mutableStateOf("") }
    val telefono = rememberSaveable { mutableStateOf("") }

    val idRollUsuario = rememberSaveable { mutableStateOf("") }
    val nombreRollUsuario = rememberSaveable { mutableStateOf("") }
    val descripcion = rememberSaveable { mutableStateOf("") }
    val estadoRollUsuario = rememberSaveable { mutableStateOf("Activo") }

    val mostrarUsuario = rememberSaveable { mutableStateOf(false) }
    val mostrarRol = rememberSaveable { mutableStateOf(false) }
    val mostrarQR = rememberSaveable { mutableStateOf(false) }

    if (!uiState.razones.isNullOrEmpty()) {
        Toast.makeText(context, uiState.razones, Toast.LENGTH_LONG).show()
        viewModel.onUserEvent(UserEvent.LimpiarMensaje)
    }

    if (busqueda.value.isNotBlank())
        viewModel.onUserEvent(UserEvent.BuscarUsuario(busqueda.value))
    else
        viewModel.onUserEvent(UserEvent.MostrarUserEvent)

    if (uiState.roles.isEmpty())
        viewModel.onUserEvent(UserEvent.GetRoles)

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
            Column(
                //Contenedor
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 100.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Titulo("Usuarios", Icons.Outlined.AccountCircle)
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    Row {
                        Box(modifier = Modifier.weight(3f)) {
                            Buscador(busqueda.value) { busqueda.value = it }
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            SwitchInactivos(uiState.switch){
                                viewModel.onUserEvent(UserEvent.Switch)
                            }
                        }
                    }
                    AreaUsuarios(uiState.usuarios, uiState.roles, uiState.switch, idUsuario, nombre, apellido, correo, clave, telefono, rol, mostrarUsuario,
                        onClickRestaurar = { usuario ->
                            {
                                viewModel.onUserEvent(
                                    UserEvent.EditarUsuario(
                                        usuario(
                                            id_usuario = usuario.id_usuario,
                                            id_roll_usuario = usuario.id_roll_usuario,
                                            nombre = usuario.nombre,
                                            apellido = usuario.apellido,
                                            email = usuario.email,
                                            clave = usuario.clave,
                                            telefono = usuario.telefono,
                                            estado = true
                                        )
                                    )
                                )
                                mostrarDialogo.value = false
                            }.confirmar(
                                mensaje = "¿Estás seguro que deseas restaurar el usuario '${usuario.nombre}'?",
                                showDialog = { mostrarDialogo.value = true },
                                setMessage = { confirmarMensaje.value = it },
                                setAction = { accionDeConfirmacion.value = it }
                            )
                        }, onClickEliminar = { usuario ->
                            {
                                viewModel.onUserEvent(
                                    UserEvent.BorrarUsuario(
                                        usuario(
                                            id_usuario = usuario.id_usuario,
                                            id_roll_usuario = usuario.id_roll_usuario,
                                            nombre = usuario.nombre,
                                            apellido = usuario.apellido,
                                            email = usuario.email,
                                            clave = usuario.clave,
                                            telefono = usuario.telefono,
                                            estado = false
                                        )
                                    )
                                )
                                mostrarDialogo.value = false
                            }.confirmar(
                                mensaje = "¿Estás seguro que deseas eliminar el usuario '${usuario.nombre}'?",
                                showDialog = { mostrarDialogo.value = true },
                                setMessage = { confirmarMensaje.value = it },
                                setAction = { accionDeConfirmacion.value = it }
                            )
                        }
                    )
                }
                Botones(mostrarQR, mostrarUsuario, idUsuario, nombre, apellido, correo, clave, telefono, rol)
            }
        }
        DialogoUsuario(mostrarUsuario, idUsuario, nombre, apellido, correo, clave, telefono, rol, uiState.roles, mostrarRol){
            try {
                if (idUsuario.value.isEmpty() || nombre.value.isEmpty() || apellido.value.isEmpty() || correo.value.isEmpty() || clave.value.isEmpty() || telefono.value.isEmpty() || rol.value.isEmpty()) {
                    throw Exception("Campos vacios.")
                }

                if (uiState.usuarios.find { it.id_usuario == idUsuario.value.toLong() } != null) {
                    viewModel.onUserEvent(
                        UserEvent.EditarUsuario(
                            usuario(
                                id_usuario = idUsuario.value.toLong(),
                                id_roll_usuario = uiState.roles.find { it.nombre == rol.value }?.id_roll_usuario
                                    ?: 0,
                                nombre = nombre.value,
                                apellido = apellido.value,
                                email = correo.value,
                                clave = clave.value,
                                telefono = telefono.value,
                                estado = true
                            )
                        )
                    )
                } else {
                    viewModel.onUserEvent(
                        UserEvent.AgregarUsuario(
                            usuario(
                                id_usuario = idUsuario.value.toLong(),
                                id_roll_usuario = uiState.roles.find { it.nombre == rol.value }?.id_roll_usuario
                                    ?: 0,
                                nombre = nombre.value,
                                apellido = apellido.value,
                                email = correo.value,
                                clave = clave.value,
                                telefono = telefono.value,
                                estado = true
                            )
                        )
                    )
                }

                idUsuario.value = ""
                nombre.value = ""
                apellido.value = ""
                correo.value = ""
                clave.value = ""
                telefono.value = ""
                rol.value = ""
            } catch (_: Exception) { }
        }
        DialogoRol(mostrarRol, idRollUsuario, nombreRollUsuario, descripcion, estadoRollUsuario, uiState.roles,
            onClickGuardar = {
                try {
                    if (idRollUsuario.value.isEmpty() || nombreRollUsuario.value.isEmpty() || descripcion.value.isEmpty() || estadoRollUsuario.value.isEmpty()) {
                        throw Exception("Campos vacios.")
                    }

                    if (uiState.roles.find { it.id_roll_usuario == idRollUsuario.value.toLong() } != null) {
                        viewModel.onUserEvent(
                            UserEvent.EditarRol(
                                roll_usuarios(
                                    id_roll_usuario = idRollUsuario.value.toLong(),
                                    nombre = nombreRollUsuario.value,
                                    descripcion = descripcion.value,
                                    estado = estadoRollUsuario.value.formatoActivoDDBB()
                                )
                            )
                        )
                    } else {
                        viewModel.onUserEvent(
                            UserEvent.AgregarNuevoRol(
                                roll_usuarios(
                                    id_roll_usuario = idRollUsuario.value.toLong(),
                                    nombre = nombreRollUsuario.value,
                                    descripcion = descripcion.value,
                                    estado = estadoRollUsuario.value.formatoActivoDDBB()
                                )
                            )
                        )
                    }

                    idRollUsuario.value = ""
                    nombreRollUsuario.value = ""
                    descripcion.value = ""
                    estadoRollUsuario.value = "Activo"
                } catch (_: Exception) { }
            }, onClickEliminar = {
                {
                    try {
                        if (idRollUsuario.value.isEmpty() || nombreRollUsuario.value.isEmpty() || descripcion.value.isEmpty() || estadoRollUsuario.value.isEmpty()) {
                            throw Exception("Campos vacios.")
                        }
                    } catch (_: Exception) { }

                    viewModel.onUserEvent(
                        UserEvent.ElimnarRoll(
                            roll_usuarios(
                                idRollUsuario.value.toLong(),
                                nombreRollUsuario.value,
                                descripcion.value,
                                true
                            )
                        )
                    )

                    idRollUsuario.value = ""
                    nombreRollUsuario.value = ""
                    descripcion.value = ""
                    estadoRollUsuario.value = "Activo"

                    mostrarDialogo.value = false
                }.confirmar(
                    mensaje = "¿Estás seguro que deseas eliminar el rol '${nombreRollUsuario.value}'?",
                    showDialog = { mostrarDialogo.value = true },
                    setMessage = { confirmarMensaje.value = it },
                    setAction = { accionDeConfirmacion.value = it }
                )
            }
        )
        DialogoQR(mostrarQR, uiState.qr.toString())
        DialogoConfirmacion(
            showDialog = mostrarDialogo,
            confirmMessage = confirmarMensaje,
            onConfirmAction = accionDeConfirmacion
        )
        MenuLateral(navController)
    }
}