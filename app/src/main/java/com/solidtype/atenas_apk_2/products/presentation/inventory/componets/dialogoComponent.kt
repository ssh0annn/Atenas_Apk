package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.solidtype.atenas_apk_2.R

@Composable
fun Dialogo(
    mostrar: Boolean,
    onCerrarDialogo: () -> Unit = {}
) {
    if (mostrar) {
        Dialog(
            onDismissRequest = {
                onCerrarDialogo()
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(
                            //Desgradado
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFADB4C9),
                                    Color(0xFF73718B),
                                )
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text ="Ejemplar de Excel", modifier = Modifier)
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ejemplar),
                            contentDescription = "Ejemplar de Excel",
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Boton("Cerrar") {
                            onCerrarDialogo()
                        }
                    }
                }
            }
        )
    }
}