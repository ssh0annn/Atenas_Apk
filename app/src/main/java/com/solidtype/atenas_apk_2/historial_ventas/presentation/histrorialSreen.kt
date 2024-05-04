package com.solidtype.atenas_apk_2.historial_ventas.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad


@Composable
fun Prueba(
    viewmodel: HistorailViewModel = hiltViewModel()
){
    Text("holaa")
}


@Composable
fun Screen(historialVentaEntidad: HistorialVentaEntidad, onProductClick:(HistorialVentaEntidad) -> Unit){
    Column(
        modifier = Modifier
            .clickable { onProductClick(historialVentaEntidad) }
            .padding(10.dp)
            .width(122.dp)
            .height(170.dp)
            .clip(RoundedCornerShape(10.dp)),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Cod: ${historialVentaEntidad.Codigo}", textAlign = TextAlign.Center)
        Text("Cod: ${historialVentaEntidad.Nombre}", textAlign = TextAlign.Center)
    }
}