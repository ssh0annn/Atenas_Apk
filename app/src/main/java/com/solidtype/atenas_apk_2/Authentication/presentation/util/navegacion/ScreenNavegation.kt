package com.solidtype.atenas_apk_2.Authentication.presentation.util.navegacion

sealed class Router (val route: String) {
    object Register: Router("Register")
    object Login: Router("Login")
}