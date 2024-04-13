package com.solidtype.atenas_apk_2.Authentication.presentation.home.component

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.Authentication.presentation.home.HomeViewModel
import com.solidtype.atenas_apk_2.core.pantallas.Screens

@Composable
fun HmeScreen(nav: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val name: String by  viewModel.name.observeAsState(initial = "")
    val logeado:Boolean by viewModel.logeado.observeAsState(initial = true)
    val context = LocalContext.current

    if (!logeado) {
        nav.navigate(Screens.Login.route)
    } else {
        LazyColumn(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    color = Color(
                        android.graphics.Color.parseColor("#ffffff")
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Hola, $name!",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor("#343341"))
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        viewModel.deslogear()
                        Toast.makeText(context, "Te has deslogueado con exito!", Toast.LENGTH_LONG).show()
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
                        "Log Out",
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}
