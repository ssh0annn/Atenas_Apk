package com.solidtype.atenas_apk_2.util
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
@Composable
fun CustomSnackbar() {
    var snackbarVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { snackbarVisible = true }) {
            Text("Ver mensaje")
        }

        if (snackbarVisible) {
            LaunchedEffect(Unit) {
                delay(4000) // Duración de 1 segundo
                snackbarVisible = false
            }
            Snackbar(
                modifier = Modifier.background(color = Color.Black),
                ) {
                Row {
                    Text(text = "La importación ha sido exitosa!", color = Color.White)
                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                    Button(onClick = {
                        Toast.makeText(context, "Clickiaste Ver", Toast.LENGTH_SHORT).show()
                    }) {
                        Text("Ver")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                    Button(onClick = {
                        Toast.makeText(
                            context,
                            "Clickiaste Compartir",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text("Compartir")
                    }


                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Contenido de la pantalla")
        CustomSnackbar()
    }
}
