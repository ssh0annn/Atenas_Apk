package com.solidtype.atenas_apk_2.util.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun Buscador(
    busqueda: String,
    largo: Boolean = false,
    onBusquedaChange: (String) -> Unit
) {
    val isFocused = rememberSaveable { mutableStateOf(false) }

    TextField(
        value = busqueda,
        onValueChange = onBusquedaChange,
        singleLine = true,
        modifier =
        if (largo) Modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(start = 25.dp, end = 5.dp, top = 4.dp, bottom = 4.dp)
            .onFocusChanged { isFocused.value = it.isFocused }
        else Modifier
            .width(Pantalla.ancho - 525.dp)
            .height(66.dp)
            .padding(start = 25.dp, end = 5.dp, top = 4.dp, bottom = 4.dp)
            .onFocusChanged { isFocused.value = it.isFocused },
        shape = RoundedCornerShape(50),
        placeholder = {
            Text(
                text = "Buscar...",
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    color = if (isFocused.value) GrisClaro else Blanco,
                    fontSize = 16.sp
                )
            )
        },
        textStyle = TextStyle(
            textAlign = TextAlign.Start,
            color = Blanco,
            fontSize = 16.sp,
            fontWeight = Bold
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "",
                modifier = Modifier
                    .background(AzulGris)
                    .width(25.dp)
                    .height(25.dp),
                tint = Blanco
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = AzulGris,
            unfocusedContainerColor = AzulGris,
            disabledContainerColor = AzulGris,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}