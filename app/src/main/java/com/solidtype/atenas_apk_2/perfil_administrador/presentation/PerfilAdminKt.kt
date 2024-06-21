package com.solidtype.atenas_apk_2.perfil_administrador.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.core.pantallas.NavigationSingleton
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.AdminViewModel
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral

@Composable
fun PerfilAdmin(navController: NavController, viewModel: AdminViewModel = hiltViewModel()) {
    val context = LocalContext.current

    AndroidView(
        factory = { PefilAdministrador(context, viewModel) },
        modifier = Modifier.fillMaxSize()
    )

    MenuLateral(navController)
}