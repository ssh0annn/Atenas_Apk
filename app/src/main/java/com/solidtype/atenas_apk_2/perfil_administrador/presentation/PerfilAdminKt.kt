package com.solidtype.atenas_apk_2.perfil_administrador.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import com.solidtype.atenas_apk_2.core.pantallas.Navigation
import com.solidtype.atenas_apk_2.core.pantallas.NavigationSingleton
import com.solidtype.atenas_apk_2.util.ui.Components.MenuLateral

fun setMenuLateralContent(view: ComposeView) {
    view.setContent {
        NavigationSingleton.navController?.let { MenuLateral(it) }
    }
}