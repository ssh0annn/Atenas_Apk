package com.solidtype.atenas_apk_2.util.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.TipoUserSingleton
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.semiTransparente
import com.solidtype.atenas_apk_2.util.ui.LogoutViewmodel
import com.solidtype.atenas_apk_2.util.ui.Pantalla

object MenuLateralSingleton {
    var menuLateral: @Composable (NavController) -> Unit = {
        MenuLateral(it)
    }
}

@Composable
fun MenuLateral(
    navController: NavController,
    viewModel: LogoutViewmodel = hiltViewModel()
) {
    val mostrarMenu = rememberSaveable { mutableStateOf(false) }
    val noHoverInteractionSource = remember { MutableInteractionSource() }

    val mostrarDialogo = remember { mutableStateOf(false) }
    val confirmarMensaje = remember { mutableStateOf("") }
    val accionDeConfirmacion = remember { mutableStateOf({}) }

    AnimatedVisibility(
        visible = mostrarMenu.value,
        enter = fadeIn(tween(500)),
        exit = fadeOut(tween(500))
    ) {
        Box( //Fondo
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = noHoverInteractionSource,
                    indication = null
                ) { if (mostrarMenu.value) mostrarMenu.value = false }
                .background(semiTransparente)
        )
    }
    AnimatedVisibility(
        //De Izquierda a Derecha
        visible = mostrarMenu.value,
        enter = slideInHorizontally(
            initialOffsetX = { -it },
            animationSpec = tween(500)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(500)
        )
    ) {
        Box {
            Box( //Menú Lateral
                modifier = Modifier
                    .width(Pantalla.ancho * 0.35f)
                    .fillMaxHeight()
                    .background(Blanco)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(5f)
                            .fillMaxWidth()
                            .padding(18.dp)
                            .verticalScroll(rememberScrollState())
                    ) {//Botones
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Titulo("Menú", Icons.Outlined.Inventory2)
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        if (TipoUserSingleton.tipoUser != TipoUser.ADMIN) {
                            Boton(
                                "Servicios",
                                anchoTotal = true,
                                //Si la pantalla actual es diferente a la pantalla de Servicios
                                habilitar = navController.currentDestination?.route != Screens.Servicio.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.Servicio.route)
                            }
                            Boton(
                                "Ventas",
                                anchoTotal = true,
                                habilitar = navController.currentDestination?.route != Screens.Ventas.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.Ventas.route)
                            }
                            Boton(
                                "Vista de Tickets",
                                anchoTotal = true,
                                habilitar = navController.currentDestination?.route != Screens.VistaTicket.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.VistaTicket.route)
                            }
                        }

                        if (TipoUserSingleton.tipoUser == TipoUser.ADMIN) {

                            Boton(
                                "Configuración del Perfil",
                                anchoTotal = true,
                                habilitar = navController.currentDestination?.route != Screens.PerfilAdmin.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.PerfilAdmin.route)
                            }
                            Boton(
                                "Inventario",
                                anchoTotal = true,
                                habilitar = navController.currentDestination?.route != Screens.Productos.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.Productos.route)
                            }
                            Boton(
                                "Historial",
                                anchoTotal = true,
                                habilitar = navController.currentDestination?.route != Screens.HistorialVentasTickets.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.HistorialVentasTickets.route)
                            }
                            Boton(
                                "Facturas",
                                anchoTotal = true,
                                habilitar = navController.currentDestination?.route != Screens.Factura.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.Factura.route)
                            }
                            Boton(
                                "Gestion de Usuarios",
                                anchoTotal = true,
                                habilitar = navController.currentDestination?.route != Screens.GestionUsuarios.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.GestionUsuarios.route)
                            }
                            Boton(
                                "Gestion de Clientes",
                                anchoTotal = true,
                                habilitar = navController.currentDestination?.route != Screens.GestionCliente.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.GestionCliente.route)
                            }
                            Boton(
                                "Gestion de Proveedores",
                                anchoTotal = true,
                                habilitar = navController.currentDestination?.route != Screens.GestionProveedores.route
                            ) {
                                mostrarMenu.value = false
                                navController.navigate(Screens.GestionProveedores.route)
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .padding(18.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.width(100.dp))
                        Text(
                            text = "Cerrar Sesión",
                            color = AzulGris,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .clickable {
                                    {
                                        navController.navigate(Screens.Login.route) {
                                            popUpTo(Screens.Login.route) {
                                                inclusive = true
                                            }
                                            launchSingleTop = true
                                        }
                                        viewModel.onEvent()
                                    }.confirmar(
                                        mensaje = "¿Estás seguro de que deseas cerrar sesión?",
                                        showDialog = { mostrarDialogo.value = true },
                                        setMessage = { confirmarMensaje.value = it },
                                        setAction = { accionDeConfirmacion.value = it }
                                    )
                                }
                        )
                    }
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = "Cerrar Menu Lateral",
                tint = AzulGris,
                modifier = Modifier
                    .padding(30.dp)
                    .size(40.dp)
                    .align(Alignment.TopStart)
                    .clickable { mostrarMenu.value = false }
            )
        }
    }
    Box {
        Box( //Menú Lateral
            modifier = Modifier
                .width(Pantalla.ancho * 0.3f)
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {//Botones
                }
                Row(
                    modifier = Modifier
                        .padding(start = 64.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    if (true) { // si no hay imagen de perfil
                        Avatar(mostrarMenu)
                    } else {//Mostrar foto de perfil
                        //Image(painter = , contentDescription = )
                    }
                }
            }
        }
    }
    DialogoConfirmacion(
        showDialog = mostrarDialogo,
        confirmMessage = confirmarMensaje,
        onConfirmAction = accionDeConfirmacion
    )
}