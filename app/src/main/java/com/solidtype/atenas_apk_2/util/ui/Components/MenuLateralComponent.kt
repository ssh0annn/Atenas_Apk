package com.solidtype.atenas_apk_2.util.ui.Components

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
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.AuthEvent
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.AuthViewmodel
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.TipoUserSingleton
import com.solidtype.atenas_apk_2.core.pantallas.NavigationSingleton
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.semiTransparente
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun MenuLateral(navController: NavController, viewModel: AuthViewmodel = hiltViewModel()) {
    val mostrarMenu = rememberSaveable { mutableStateOf(false) }
    val noHoverInteractionSource = remember { MutableInteractionSource() }

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

                        if (TipoUserSingleton.tipoUser == TipoUser.ADMIN) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Boton(
                                "Inventario",
                                anchoTotal = true,
                                habilitar = NavigationSingleton.screen != Screens.Productos.route
                            ) {
                                NavigationSingleton.screen = Screens.Productos.route
                                mostrarMenu.value = false
                                navController.navigate(Screens.Productos.route)
                            }
                            Boton(
                                "Historial",
                                anchoTotal = true,
                                habilitar = NavigationSingleton.screen != Screens.HistorialVentasTickets.route
                            ) {
                                NavigationSingleton.screen = Screens.HistorialVentasTickets.route
                                mostrarMenu.value = false
                                navController.navigate(Screens.HistorialVentasTickets.route)
                            }
                            Boton(
                                "Facturas",
                                anchoTotal = true,
                                habilitar = NavigationSingleton.screen != Screens.Factura.route
                            ) {
                                NavigationSingleton.screen = Screens.Factura.route
                                mostrarMenu.value = false
                                navController.navigate(Screens.Factura.route)
                            }
                            Boton(
                                "Gestion de Usuarios",
                                anchoTotal = true,
                                habilitar = NavigationSingleton.screen != Screens.GestionUsuarios.route
                            ) {
                                NavigationSingleton.screen = Screens.GestionUsuarios.route
                                mostrarMenu.value = false
                                navController.navigate(Screens.GestionUsuarios.route)
                            }
                            //Este botón es especial
                            Boton(
                                "Configuración del Perfil",
                                anchoTotal = true,
                                habilitar = NavigationSingleton.screen != Screens.PerfilAdmin.route
                            ) {
                                NavigationSingleton.screen = Screens.PerfilAdmin.route
                                mostrarMenu.value = false
                                navController.navigate(Screens.PerfilAdmin.route)
                            }
                            Boton(
                                "Gestion de Clientes",
                                anchoTotal = true,
                                habilitar = NavigationSingleton.screen != Screens.GestionCliente.route
                            ) {
                                NavigationSingleton.screen = Screens.GestionCliente.route
                                mostrarMenu.value = false
                                navController.navigate(Screens.GestionCliente.route)
                            }
                        }
                        Boton(
                            "Gestion de Proveedores",
                            anchoTotal = true,
                            habilitar = NavigationSingleton.screen != Screens.GestionProveedores.route
                        ) {
                            NavigationSingleton.screen = Screens.GestionProveedores.route
                            mostrarMenu.value = false
                            navController.navigate(Screens.GestionProveedores.route)
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
                            text = "Cerrar Sección",
                            color = AzulGris,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 10.dp)
                                .clickable {
                                    NavigationSingleton.screen = ""
                                    navController.navigate(Screens.Login.route)
                                    navController.popBackStack()
                                    viewModel.onEvent(AuthEvent.LogoutEvent)
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
}