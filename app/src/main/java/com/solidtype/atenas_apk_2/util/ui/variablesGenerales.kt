package com.solidtype.atenas_apk_2.util.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object  Pantalla {
    val ancho: Dp
        @Composable
        get() = LocalConfiguration.current.screenWidthDp.dp
    val alto: Dp
        @Composable
        get() = LocalConfiguration.current.screenHeightDp.dp
}