package com.solidtype.atenas_apk_2.realizar_venta.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.solidtype.atenas_apk_2.util.ui.components.Titulo

@Preview(device = "spec:width=673dp,height=841dp,orientation=landscape", backgroundColor = 0xffffff, showBackground = true)
@Composable
fun VentaScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Titulo(text = "Venta")
    }
}
