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
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.AreaUsuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.Botones
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoQR
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoRol
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoUsuario
import com.solidtype.atenas_apk_2.util.ui.components.SwitchInactivos
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.components.Buscador
import com.solidtype.atenas_apk_2.util.ui.components.DialogoConfirmacion
import com.solidtype.atenas_apk_2.util.ui.components.Loading
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.components.Titulo

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
                    AreaUsuarios(
                        uiState,
                        idUsuario,
                        nombre,
                        apellido,
                        correo,
                        clave,
                        telefono,
                        rol,
                        mostrarUsuario,
                        mostrarDialogo,
                        confirmarMensaje,
                        accionDeConfirmacion,
                        viewModel
                    )
                }
                Botones(
                    mostrarQR,
                    mostrarUsuario,
                    idUsuario,
                    nombre,
                    apellido,
                    correo,
                    clave,
                    telefono,
                    rol
                )
            }
        }
        DialogoUsuario(
            mostrarUsuario,
            idUsuario,
            nombre,
            apellido,
            correo,
            clave,
            telefono,
            rol,
            uiState,
            mostrarRol,
            viewModel
        )
        DialogoRol(
            mostrarRol,
            idRollUsuario,
            nombreRollUsuario,
            descripcion,
            estadoRollUsuario,
            uiState,
            viewModel,
            mostrarDialogo,
            confirmarMensaje,
            accionDeConfirmacion
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