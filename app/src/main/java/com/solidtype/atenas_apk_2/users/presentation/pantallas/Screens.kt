package com.solidtype.atenas_apk_2.users.presentation.pantallas

    sealed class Screens (val route:String){
        object Login: Screens("login")
        object Register: Screens("register")
        object Home: Screens("home")

    }
