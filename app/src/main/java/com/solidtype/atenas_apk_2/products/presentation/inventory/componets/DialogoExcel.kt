package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.R
import com.solidtype.atenas_apk_2.util.ui.Components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun DialogoExcel(mostrar: MutableState<Boolean>) {
    Dialogo("Ejemplar de Excel", mostrar.value, { mostrar.value = false }) {
        Image(
            painter = painterResource(id = R.drawable.ejemplar),
            contentDescription = "Ejemplar de Excel",
            modifier = Modifier
                .size(
                    width = Pantalla.ancho - 100.dp,
                    height = Pantalla.alto - 410.dp
                )
                .shadow(16.dp, shape = RoundedCornerShape(16.dp))
        )
    }
}