package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Buscador(busqueda: String = "", onBusquedaChange: (String) -> Unit) {
    TextField(
        value = busqueda,
        onValueChange = onBusquedaChange,
        singleLine = true,
        modifier = Modifier
            .width(500.dp)
            .height(66.dp)
            .padding(start = 25.dp, end = 25.dp, top = 4.dp, bottom = 4.dp)
            .background(Color(parseColor("#FFFFFF"))),
        shape = RoundedCornerShape(50),
        label = {
            Text(
                text = "Buscar...",
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    color = Color(parseColor("#FFFFFF")),
                    fontSize = 16.sp,
                    fontWeight = Bold
                )
            )
        },
        textStyle = TextStyle(
            textAlign = TextAlign.Start,
            color = Color(parseColor("#FFFFFF")),
            fontSize = 16.sp
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                modifier = Modifier.background(Color(parseColor("#343341")))
                    .width(25.dp)
                    .height(25.dp),
                tint = Color(parseColor("#FFFFFF"))
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(parseColor("#343341")),
            unfocusedContainerColor = Color(parseColor("#343341")),
            disabledContainerColor = Color(parseColor("#343341")),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}