package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun ProductsCard(modifier: Modifier = Modifier, onClickGetClient:() -> Unit){
    Card(modifier =modifier,
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {/* */}
        ) {
        Column(modifier = Modifier.padding(16.dp)){
            /*
            Aqui iria la logica de la imagen
             */
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Nombre: ")
            Text(text = "Stock:")
            Text(text = "Precio:")
            Text(text = "Costo:")
        }
    }

}




