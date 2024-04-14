package com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.core.pantallas.Screens
import com.solidtype.atenas_apk_2.historial_ventas.presentation.Screen
import org.apache.commons.io.StandardLineSeparator


@Composable
fun HomeScreen(navController: NavController){


    LazyColumn(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(
                color = Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Pantalla Factura ",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color(android.graphics.Color.parseColor("#343341"))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {navController.navigate(Screens.Servicio.route)


                },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF343341),
                    contentColor = androidx.compose.ui.graphics.Color.White,
                ),
                modifier = Modifier
                    .width(430.dp)
                    .height(100.dp)
                    .padding(top = 30.dp)
            ) {
                Text(
                    "Servicios",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {//Aqui el codigo para moverse a otra ventana
                    navController.navigate(Screens.Ticket.route)

                },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF343341),
                    contentColor = androidx.compose.ui.graphics.Color.White,
                ),
                modifier = Modifier
                    .width(430.dp)
                    .height(100.dp)
                    .padding(top = 30.dp)
            ) {
                Text(
                    "Tickets",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {//Aqui el codigo para moverse a otra ventana
                    navController.navigate(Screens.Ventas.route)
                },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF343341),
                    contentColor = androidx.compose.ui.graphics.Color.White,
                ),
                modifier = Modifier
                    .width(430.dp)
                    .height(100.dp)
                    .padding(top = 30.dp)
            ) {
                Text(
                    "Ventas",
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {//Aqui el codigo para moverse a otra ventana
                        navController.navigate(Screens.PerfilAdministrador.route)
                },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF343341),
                    contentColor = androidx.compose.ui.graphics.Color.White,
                ),
                modifier = Modifier
                    .width(430.dp)
                    .height(100.dp)
                    .padding(top = 30.dp)
            ) {
                Text(
                    "Admnistrador",
                    fontSize = 24.sp
                )
            }
        }
    }

}

