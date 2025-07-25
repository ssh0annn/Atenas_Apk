package com.solidtype.atenas_apk_2.util.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Carrito(
    fondoBlanco: Boolean = false,
    chiquito: Boolean = false
){
    Box(
        modifier = Modifier
            .size(
                when (chiquito) {
                    true -> 50.dp
                    false -> 80.dp
                }
            )
            .clip(RoundedCornerShape(50.dp))
            .background(
                Color(android.graphics.Color.parseColor(when (fondoBlanco) {
                true -> "#FFFFFF"
                false -> "#343341"
            }))
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.ShoppingCartCheckout,
            contentDescription = "Shoping Cart Checkout Icon",
            modifier = Modifier
                .background(
                    Color(android.graphics.Color.parseColor(when (fondoBlanco) {
                    true -> "#FFFFFF"
                    false -> "#343341"
                }))
                )
                .size(
                    when (chiquito) {
                        true -> 30.dp
                        false -> 70.dp
                    }
                ),
            tint = Color(android.graphics.Color.parseColor("#1C7558"))
        )
    }
}
@Composable
@Preview(showSystemUi = true)
fun PruebaCarrito(){
    Carrito(false)
}