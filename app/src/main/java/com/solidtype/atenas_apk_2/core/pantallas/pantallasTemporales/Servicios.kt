package com.solidtype.atenas_apk_2.core.pantallas.pantallasTemporales

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.facturacion.presentation.FacturacionScreen

@Composable
fun Servicios(navController: NavController){

    FacturacionScreen(navController = navController)

    /*LazyColumn(
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
                text = "Pantalla Servicios ",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color(android.graphics.Color.parseColor("#343341"))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {//Aqui el codigo para Ser a otra ventana
                    navController.navigate(Screens.Servicio.route)

                },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF343341),
                    contentColor = Color.White,
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
                    contentColor = Color.White,
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
                    contentColor = Color.White,
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
                    contentColor = Color.White,
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
    }*/


}