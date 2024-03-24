package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BotonIconCircular(save: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .width(65.dp)
            .height(65.dp)
            .background(Color(0xFF343341)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF000000),
            contentColor = Color(0xFF1C7558),
        ),
        onClick = onClick
    ) {
        Icon(
            imageVector = when (save) {
                true -> Icons.Filled.Close
                false -> Icons.Filled.Done
            },
            contentDescription = when (save) {
                true -> "Close Icon"
                false -> "Save Icon"
            },
            modifier = Modifier
                .background(Color(0xFF000000))
                .width(80.dp)
                .height(80.dp),
            tint = Color(0XFF1C7558)
        )
    }
}