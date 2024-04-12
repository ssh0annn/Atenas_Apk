package com.solidtype.atenas_apk_2.Authentication.presentation.pantallas

    sealed class Screens (val route:String){
        object Login: Screens("login")
        object Register: Screens("register")
        object Home: Screens("home")
        object Productos:Screens("productos")

        object HistorialVentasTickets: Screens("historialventas")
        object Ventas: Screens("ventas")
        object PerfilAdministrador: Screens("perfilAdministrador")
        object GestionUsuarios: Screens("gestionUsuario")
        object GestiondeTicket: Screens("gestionTicket")








    }
