package com.solidtype.atenas_apk_2.util.ui.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris

@Composable
fun Boton(text: String = "", onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(10.dp)
            .width(120.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(AzulGris),
        colors = ButtonDefaults.buttonColors(
            containerColor = AzulGris
        )
    ) {
        Text(text)
    }
}