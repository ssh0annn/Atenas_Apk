package com.solidtype.atenas_apk_2.core.pantallas

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.solidtype.atenas_apk_2.authentication.presentation.login.component.LoginScreen
import com.solidtype.atenas_apk_2.authentication.presentation.register.OutlinedTextFieldExample

import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.GestionProductos
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.GestionUsuarios
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.GestiondeTicket
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.HomeScreen
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.Inventario
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.PerfilAdministrador
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.Ticket
import com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales.Ventas
import com.solidtype.atenas_apk_2.facturacion.presentation.FacturacionScreen
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.GestionUsuariosScreen
import com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.HistorialScreen
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventoryScreen

import com.solidtype.atenas_apk_2.servicios.servicios

//import com.solidtype.atenas_apk_2.products.presentation.inventory.InventoryScreenV2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val context = LocalContext.current

    val navController = rememberNavController()

    NavHost(

        navController = navController,
        startDestination = Screens.Login.route

    ){
        composable(route = Screens.Login.route ) {
            LoginScreen(context,navController)
        }
        composable(
            route = Screens.Register.route,
            arguments = listOf(
                navArgument(
                    name = "userId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            OutlinedTextFieldExample(navController)
        }
       /* composable(route = Screens.Home.route ) {
            HomeScreen(navController)
        }

        */

        composable(route = Screens.PerfilAdministrador.route ) {
            PerfilAdministrador(navController)
        }
        composable(route = Screens.Home.route ) {
            HomeScreen(navController)
        }
        composable(route = Screens.GestiondeTicket.route ) {
            GestiondeTicket(navController)
        }
        composable(route = Screens.GestionProducto.route ) {
            GestionProductos(navController)
        }
        composable(route = Screens.GestionUsuarios.route ) {
            GestionUsuariosScreen(navController)
        }
        composable(route = Screens.HistorialVentasTickets.route ) {
          HistorialScreen(navController)
        }
        composable(route = Screens.Inventario.route ) {
            Inventario(navController)
        }
        composable(route = Screens.Servicio.route ) {
            servicios(/*navController*/)
        }
        composable(route = Screens.Ticket.route ) {
            Ticket(navController)
        }
        composable(route = Screens.Ventas.route ) {
            Ventas(navController)
        }
        composable(route = Screens.Productos.route ) {
            InventoryScreen(navController)
        }
        composable(route = Screens.Factura.route ) {
            FacturacionScreen(navController)
        }
    }

}
