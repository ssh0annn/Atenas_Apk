package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity

@Composable
fun CardProduct(productEntity: ProductEntity, onProductClick:(ProductEntity) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onProductClick(productEntity) }
            .padding(10.dp)
            .width(100.dp)
            .height(135.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(android.graphics.Color.parseColor("#FFFFFF"))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Carrito(false)
        Spacer(modifier = Modifier.height(3.dp))
        Text("Cod: ${productEntity.Code_Product}")
        Text("Nom: ${productEntity.Name_Product}")
        Text("Stock: ${productEntity.Count_Product}")

    }
}