package com.solidtype.atenas_apk_2.core.pantallas

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.LoginScreen
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.GestionProductos
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.GestiondeTicket
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.HomeScreen
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.Inventario
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.PerfilAdministrador
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.Ticket
import com.solidtype.atenas_apk_2.facturacion.presentation.FacturacionScreen
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.ClienteScreen
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor.ProveedorScreen
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.GestionUsuariosScreen
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorialScreen
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.PerfilAdminScreen
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventoryScreen
import com.solidtype.atenas_apk_2.realizar_venta.presentation.VentaScreen
import com.solidtype.atenas_apk_2.servicios.presentation.servicios.servicios
import com.solidtype.atenas_apk_2.servicios.presentation.ticket.ticketScreen

@SuppressLint("StaticFieldLeak")
object NavigationSingleton {
    var navController: NavController? = null
}

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavigationSingleton.navController = navController

    val time = 700

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
        enterTransition = { fadeIn(animationSpec = tween(time)) + slideIntoContainer( AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(time)) },
        exitTransition = { fadeOut(animationSpec = tween(time)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(time)) },
        popEnterTransition = { fadeIn(animationSpec = tween(time)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(time)) },
        popExitTransition = { fadeOut(animationSpec = tween(time)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(time)) }
    ) {
        composable(route = Screens.Splash.route) {
            Splash(navController)
        }
        composable(route = Screens.Login.route) {
            LoginScreen(navController)
        }
        composable(Screens.PerfilAdmin.route) {
            PerfilAdminScreen(navController)
        }
        composable(route = Screens.PerfilAdministrador.route) {
            PerfilAdministrador(navController)
        }
        composable(route = Screens.Home.route + "/{usuario}") {
            HomeScreen(navController, it.arguments?.getString("usuario").orEmpty())
        }
        composable(route = Screens.GestiondeTicket.route) {
            GestiondeTicket(navController)
        }
        composable(route = Screens.GestionProducto.route) {
            GestionProductos(navController)
        }
        composable(route = Screens.GestionUsuarios.route) {
            GestionUsuariosScreen(navController)
        }
        composable(route = Screens.HistorialVentasTickets.route) {
            HistorialScreen(navController)
        }
        composable(route = Screens.Inventario.route) {
            Inventario(navController)
        }
        composable(route = Screens.Servicio.route) {
            servicios(navController)
        }
        composable(route = Screens.Ticket.route) {
            Ticket(navController)
        }
        composable(route = Screens.Ventas.route) {
            VentaScreen(navController)
        }
        composable(route = Screens.Productos.route) {
            InventoryScreen(navController)
        }
        composable(route = Screens.Factura.route) {
            FacturacionScreen(navController)
        }
        composable(route = Screens.GestionCliente.route) {
            ClienteScreen(navController)
        }
        composable(route = Screens.GestionProveedores.route) {
            ProveedorScreen(navController)
        }
        composable(route = Screens.VistaTicket.route) {
            ticketScreen(navController)
        }
    }

}
