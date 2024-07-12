package com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateral
import com.solidtype.atenas_apk_2.util.ui.components.Titulo

@Composable
fun VentasScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Titulo("Ventas Screen")
    }
    MenuLateral(navController)
}