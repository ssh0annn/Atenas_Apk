package com.solidtype.atenas_apk_2.util.ui.Components

import android.content.Intent
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
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.PefilAdministrador
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.semiTransparente
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun MenuLateral(navController: NavController) {
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
                        Spacer(modifier = Modifier.height(20.dp))
                        Boton("Inventario", anchoTotal = true) {
                            navController.navigate(Screens.Productos.route)
                            mostrarMenu.value = false
                            PefilAdministrador.instancia?.finish()
                        }
                        Boton("Historial", anchoTotal = true) {
                            navController.navigate(Screens.HistorialVentasTickets.route)
                            mostrarMenu.value = false
                            PefilAdministrador.instancia?.finish()
                        }
                        Boton("Facturas", anchoTotal = true) {
                            navController.navigate(Screens.Factura.route)
                            mostrarMenu.value = false
                            PefilAdministrador.instancia?.finish()
                        }
                        Boton("Gestion de Usuarios", anchoTotal = true) {
                            navController.navigate(Screens.GestionUsuarios.route)
                            mostrarMenu.value = false
                            PefilAdministrador.instancia?.finish()
                        }
                        //Este botón es especial
                        Boton("Configuración del Perfil", anchoTotal = true) {
                            PefilAdministrador.instancia?.finish()
                            navController.context.startActivity(Intent(navController.context, PefilAdministrador::class.java))
                            mostrarMenu.value = false
                        }
                        Boton("Gestion de Clientes", anchoTotal = true) {
                            navController.navigate(Screens.GestionCliente.route)
                            mostrarMenu.value = false
                            PefilAdministrador.instancia?.finish()
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