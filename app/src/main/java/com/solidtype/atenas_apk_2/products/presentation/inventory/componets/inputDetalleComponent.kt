package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputDetalle(text: String, corto: Boolean = false, onValueChange: (String) -> Unit) {
    TextField(
        value = "",
        onValueChange = onValueChange,
        singleLine = true,
        modifier = Modifier
            .width(when (corto) {
                true -> 200.dp
                false -> 270.dp
            })
            .height(50.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(android.graphics.Color.parseColor("#FFFFFF"))),
        label = {
            Text(
                text = text,
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    color = Color(android.graphics.Color.parseColor("#000000")),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    )
}