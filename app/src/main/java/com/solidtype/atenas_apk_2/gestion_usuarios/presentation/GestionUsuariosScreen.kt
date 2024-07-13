package com.solidtype.atenas_apk_2.gestion_usuarios.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.AreaUsuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.AvatarConBotones
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.Detalles
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoConfirmarEliminarRol
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoConfirmarEliminarUsuario
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoSimple
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Components.Buscador
import com.solidtype.atenas_apk_2.util.ui.Components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.Components.Titulo

@Composable
fun GestionUsuariosScreen(
    navController: NavController,
    viewModel: UsuariosViewmodel = hiltViewModel()
) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val busqueda = rememberSaveable { mutableStateOf("") }

    val rol = rememberSaveable { mutableStateOf("") }
    val idUsuario = rememberSaveable { mutableStateOf("") }
    val nombre = rememberSaveable { mutableStateOf("") }
    val apellido = rememberSaveable { mutableStateOf("") }
    val correo = rememberSaveable { mutableStateOf("") }
    val clave = rememberSaveable { mutableStateOf("") }
    val telefono = rememberSaveable { mutableStateOf("") }
    val estado = rememberSaveable { mutableStateOf("") }

    val idRollUsuario = rememberSaveable { mutableStateOf("") }
    val nombreRollUsuario = rememberSaveable { mutableStateOf("") }
    val descripcion = rememberSaveable { mutableStateOf("") }
    val estadoRollUsuario = rememberSaveable { mutableStateOf("") }

    val mostrarDialogo = rememberSaveable { mutableStateOf(false) }
    val mostrarConfirmar = rememberSaveable { mutableStateOf(false) }
    val mostrarConfirmarRol = rememberSaveable { mutableStateOf(false) }
    val mostrarQR = rememberSaveable { mutableStateOf(false) }

    if(busqueda.value.isNotBlank()) {
        viewModel.onUserEvent(UserEvent.BuscarUsuario(busqueda.value))
        Log.i("GestionUsuariosScreen", "Buscando usuario")
    }else{
        viewModel.onUserEvent(UserEvent.MostrarUserEvent)
        Log.i("GestionUsuariosScreen", "Todos los usuarios")
    }

    if (uiState.roles.isEmpty())
        viewModel.onUserEvent(UserEvent.GetRoles)

    if (false) {
        navController.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        Column( //All
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(GrisClaro),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column( //Contenedor
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 100.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Titulo("Usuarios", Icons.Outlined.AccountCircle)
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {
                        Buscador(busqueda.value) { busqueda.value = it }
                        AreaUsuarios(uiState, idUsuario, nombre, apellido, correo, clave, telefono, estado, rol)
                    }
                    Detalles(idUsuario, nombre, apellido, correo, clave, telefono, rol, uiState, mostrarDialogo, mostrarConfirmar, estado, viewModel, context)
                }
                AvatarConBotones(mostrarQR)
            }
        }
        DialogoSimple(mostrarDialogo, mostrarConfirmarRol, idRollUsuario, nombreRollUsuario, descripcion, estadoRollUsuario, uiState, viewModel, context)
        DialogoConfirmarEliminarUsuario(mostrarConfirmar, viewModel, idUsuario, uiState, rol, nombre, apellido, correo, clave, telefono, estado, context)
        DialogoConfirmarEliminarRol(mostrarConfirmarRol, viewModel, idRollUsuario, nombreRollUsuario, descripcion, estadoRollUsuario, context)
        Dialogo(titulo = "QR de Técnico", mostrar = mostrarQR.value, onCerrarDialogo = { mostrarQR.value = false }, max = false) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                //QR
            }
        }
        MenuLateral(navController)

        Button(
            onClick = { viewModel.getSyncpersona()},
            modifier = Modifier.fillMaxWidth(),
        ) {
                Text(text = "Prueba")
        }
    }
}