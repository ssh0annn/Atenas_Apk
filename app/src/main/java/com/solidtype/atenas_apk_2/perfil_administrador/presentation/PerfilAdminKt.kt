package com.solidtype.atenas_apk_2.perfil_administrador.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.core.pantallas.NavigationSingleton
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui.AdminViewModel
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral

@Composable
fun PerfilAdmin(navController: NavController, viewModel: AdminViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if(!uiState.perfilAdmin.isEmpty()){
        print(uiState.perfilAdmin.first())
        AndroidView(
            factory = { PefilAdministrador(context, uiState) },
            modifier = Modifier.fillMaxSize()
        )
    }
    MenuLateral(navController)
}