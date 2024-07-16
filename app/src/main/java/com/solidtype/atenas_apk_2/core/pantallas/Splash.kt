package com.solidtype.atenas_apk_2.core.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.R
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.util.ui.components.Loading
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_progress),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Eficiencia\nRapidez",
            textAlign = TextAlign.Center,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = AzulGris
        )
        Spacer(Modifier.height(70.dp))
        Text(
            text = "Eficiencia + Rapidez  = Éxito\nAgregas tus productos\ny servicios",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = AzulGris
        )
        Spacer(Modifier.height(32.dp))
        Loading()
    }

    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(Screens.Login.route){
            popUpTo(Screens.Splash.route){
                inclusive = true
            }
        }
    }
}