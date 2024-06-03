package com.solidtype.atenas_apk_2.util.ui.Components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco

@Composable
fun BotonBlanco(
    text: String,
    modifier: Modifier = Modifier,
    size: Int = -1,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        elevation =  ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blanco, contentColor = AzulGris
        ),
        modifier = modifier
    ) {
        Text(text = text, fontSize = if (size != -1) size.sp else TextUnit.Unspecified)
    }
}