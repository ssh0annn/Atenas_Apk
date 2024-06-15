package com.solidtype.atenas_apk_2.util.ui.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco

@Composable
fun Boton(
    text: String = "",
    habilitar: Boolean = true,
    anchoTotal: Boolean = false,
    icon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Button(
        enabled = habilitar,
        elevation =  ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp
        ),
        onClick = onClick,
        modifier =
        if(icon != null) Modifier
            .size(80.dp)
            .padding(5.dp)
        else if(anchoTotal)
            Modifier
                .padding(10.dp)
                .height(50.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
        else Modifier
            .padding(10.dp)
            .width(120.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = Blanco,
            containerColor = AzulGris
        )
    ) {
        if (icon != null) {
            icon()
        } else {
            Text(
                text = text,
                color = Blanco
            )
        }
    }
}