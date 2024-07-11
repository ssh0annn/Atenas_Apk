package com.solidtype.atenas_apk_2.gestion_usuarios.presentation

import android.util.Log
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
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoConfirmarEliminarRol
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoConfirmarEliminarUsuario
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoQR
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.DialogoRol
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoUsuario
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.components.Buscador
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

    val busqueda = rememberSaveable { mutableStateOf("") }

    val rol = rememberSaveable { mutableStateOf("") }
    val idUsuario = rememberSaveable { mutableStateOf("") }
    val nombre = rememberSaveable { mutableStateOf("") }
    val apellido = rememberSaveable { mutableStateOf("") }
    val correo = rememberSaveable { mutableStateOf("") }
    val clave = rememberSaveable { mutableStateOf("") }
    val telefono = rememberSaveable { mutableStateOf("") }
    val estado = rememberSaveable { mutableStateOf("Activo") }

    val idRollUsuario = rememberSaveable { mutableStateOf("") }
    val nombreRollUsuario = rememberSaveable { mutableStateOf("") }
    val descripcion = rememberSaveable { mutableStateOf("") }
    val estadoRollUsuario = rememberSaveable { mutableStateOf("Activo") }

    val editar = rememberSaveable { mutableStateOf(false) }
    val mostrarUsuario = rememberSaveable { mutableStateOf(false) }
    val mostrarConfirmarUsuario = rememberSaveable { mutableStateOf(false) }
    val mostrarRol = rememberSaveable { mutableStateOf(false) }
    val mostrarConfirmarRol = rememberSaveable { mutableStateOf(false) }
    val mostrarQR = rememberSaveable { mutableStateOf(false) }

    if (!uiState.razones.isNullOrEmpty()) {
        Toast.makeText(context, uiState.razones, Toast.LENGTH_LONG).show()
        viewModel.onUserEvent(UserEvent.LimpiarMensaje)
    }

    if (busqueda.value.isNotBlank()) {
        viewModel.onUserEvent(UserEvent.BuscarUsuario(busqueda.value))
        Log.i("GestionUsuariosScreen", "Buscando usuario")
    } else {
        viewModel.onUserEvent(UserEvent.MostrarUserEvent)
        Log.i("GestionUsuariosScreen", "Todos los usuarios")
    }

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
                        Buscador(busqueda.value) { busqueda.value = it }
                        /*SwitchInactivos(uiState.switch){
                            viewModel.onUserEvent(UserEvent.Switch)
                        }*/
                    }
                    AreaUsuarios(
                        uiState,
                        idUsuario,
                        nombre,
                        apellido,
                        correo,
                        clave,
                        telefono,
                        estado,
                        rol,
                        mostrarUsuario,
                        editar,
                        mostrarConfirmarUsuario
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
                    estado,
                    rol,
                    editar
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
            estado,
            viewModel,
            context
        )
        DialogoConfirmarEliminarUsuario(
            mostrarConfirmarUsuario,
            viewModel,
            idUsuario,
            uiState,
            rol,
            nombre,
            apellido,
            correo,
            clave,
            telefono,
            estado,
            context
        )
        DialogoRol(
            mostrarRol,
            mostrarConfirmarRol,
            idRollUsuario,
            nombreRollUsuario,
            descripcion,
            estadoRollUsuario,
            uiState,
            viewModel,
            context
        )
        DialogoConfirmarEliminarRol(
            mostrarConfirmarRol,
            viewModel,
            idRollUsuario,
            nombreRollUsuario,
            descripcion,
            estadoRollUsuario,
            context
        )
        DialogoQR(mostrarQR, uiState.qr.toString())
        MenuLateral(navController)
    }
}