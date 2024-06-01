package com.solidtype.atenas_apk_2.core.pantallas

sealed class Screens(val route: String) {
    object Login : Screens("login")
    object Register : Screens("register")
    object Home : Screens("home")
    object Productos : Screens("productos")
    object HistorialVentasTickets : Screens("historialventas")
    object Ventas : Screens("ventas")
    object PerfilAdministrador : Screens("perfilAdministrador")
    object GestiondeTicket : Screens("gestionTicket")

    //administrador
    object GestionUsuarios : Screens("gestionUsuario")
//    object GestionUsuario : Screens("gestion_usuario")
    object GestionProducto : Screens("gestion_producto")
    object Inventario : Screens("invenario")
    object Factura : Screens("factura")

    //vendedor
    object Ticket : Screens("ticket")
    object Venta : Screens("venta")
    object Servicio : Screens("servicio")

}