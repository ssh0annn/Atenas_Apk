package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BotonBlanco(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFFFFF), contentColor = Color(0xFF000000)
        ),
    ) {
        Text(text)
    }
}