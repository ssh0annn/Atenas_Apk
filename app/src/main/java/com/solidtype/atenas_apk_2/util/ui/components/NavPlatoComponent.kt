package com.solidtype.atenas_apk_2.util.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavPlato(
    pantalla: String,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {//Tab Navigator
        Box( //plato
            modifier = Modifier
                .width(300.dp)
                .height(60.dp)
                .background(Color(0xFFA3A1A6), shape = RoundedCornerShape(100.dp))
        ) { /* de lujo */ }
        Row(
            //separar los botones o bolas
            modifier = Modifier
                .padding(10.dp)
                .width(300.dp)
                .height(120.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {//bolas
            Box(//Servicios
                modifier = Modifier
                    .padding(bottom = if (pantalla == "servicio") 10.dp else 0.dp)
                    .size(if (pantalla == "servicio") 80.dp else 70.dp)
                    .shadow(5.dp, shape = RoundedCornerShape(100.dp))
                    .background(Color(0xFF323341), shape = RoundedCornerShape(100.dp))
                    .clickable {
                        //Navegar a Servicios
                        navController.navigate("servicio")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Work,
                    contentDescription = "",
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .size(40.dp)
                )
            }
            Box(//CheckOut
                modifier = Modifier
                    .padding(bottom = if (pantalla == "CheckOut") 10.dp else 0.dp)
                    .size(if (pantalla == "CheckOut") 80.dp else 70.dp)
                    .background(Color(0xFF323341), shape = RoundedCornerShape(100.dp))
                    .clickable {
                        //Navegar a CheckOut
                        navController.navigate("CheckOut")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "",
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .size(40.dp)
                )
            }
            Box(//Facturas
                modifier = Modifier
                    .padding(bottom = if (pantalla == "factura") 10.dp else 0.dp)
                    .size(if (pantalla == "factura") 80.dp else 70.dp)
                    .background(Color(0xFF323341), shape = RoundedCornerShape(100.dp))
                    .clickable {
                        //Navegar a Facturas
                        navController.navigate("factura")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.FactCheck,
                    contentDescription = "",
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }
    }
}