package com.solidtype.atenas_apk_2.servicios.presentation.servicios

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.core.pantallas.NavigationSingleton.navController
import com.solidtype.atenas_apk_2.util.ui.components.MenuLateral

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun servicios (){

    complementari()

}


@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun InventoryScreenPreview() {
    //servicios()
}
