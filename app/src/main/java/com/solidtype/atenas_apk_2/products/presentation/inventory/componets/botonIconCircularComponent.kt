package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BotonIconCircular(save: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .background(Color(0xFF000000), RoundedCornerShape(50))
            .clickable { onClick() }
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
                //.background(Color(0xFF000000))
                .width(80.dp)
                .height(80.dp),
            tint = Color(0XFF1C7558)
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun BotonIconCircularPreview() {
    BotonIconCircular(save = true, onClick = {})
}