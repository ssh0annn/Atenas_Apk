package com.solidtype.atenas_apk_2.core.pantallas

sealed class Screens(val route: String) {
    object Login : Screens("login")
    object Home : Screens("home")
    object Productos : Screens("productos")
    object HistorialVentasTickets : Screens("historialVentas")
    object Ventas : Screens("ventas")
    object PerfilAdministrador : Screens("perfilAdministrador")
    object GestiondeTicket : Screens("gestionTicket")

    //administrador
    object GestionUsuarios : Screens("gestionUsuario")
//    object GestionUsuario : Screens("gestion_usuario")
    object GestionProducto : Screens("gestionProducto")
    object Inventario : Screens("invenario")
    object Factura : Screens("factura")
    object GestionCliente : Screens("gestionCliente")
    object GestionProveedores: Screens("gestionProveedores")
    object PerfilAdmin : Screens("perfilAdmin")
    object VistaTicket : Screens("vistaTicket")

    //vendedor
    object Ticket : Screens("ticket")
    object Venta : Screens("venta")
    object Servicio : Screens("servicio")

}