package com.solidtype.atenas_apk_2.facturacion.presentation.facturacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FacturacionScreen(){
    Column(
        //To.do
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(
                Color(0xFFD7D7D9)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Hola Humanos! (-_-)")
    }
}

//Preview para Vortex T10M (T10MPROPLUS) Horizontal
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun FacturacionScreenPreview() {
    FacturacionScreen()
}