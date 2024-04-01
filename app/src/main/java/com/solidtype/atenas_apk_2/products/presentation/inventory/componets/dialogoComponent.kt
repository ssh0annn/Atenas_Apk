package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                        .height(245.dp)
                        .background(
                            //Desgradado
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xAACCD2E4),
                                    Color(0xAA727694),
                                )
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .blur(5.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0x00FFFFFF),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xAAFFFFFF),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text ="Ejemplar de Excel",
                            color = Color(0xFF343341),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                            )
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ejemplar),
                            contentDescription = "Ejemplar de Excel",
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(16.dp, shape = RoundedCornerShape(16.dp))
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

@Preview()
@Composable
fun DialogoPreview() {
    Dialogo(mostrar = true)
}
