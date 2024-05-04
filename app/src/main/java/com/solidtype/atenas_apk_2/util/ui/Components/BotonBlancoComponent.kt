package com.solidtype.atenas_apk_2.util.ui.Components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun BotonBlanco(
    text: String,
    modifier: Modifier = Modifier,
    size: Int = -1,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFFFFF), contentColor = Color(0xFF000000)
        ),
        modifier = modifier
    ) {
        Text(text = text, fontSize = if (size != -1) size.sp else TextUnit.Unspecified)
    }
}