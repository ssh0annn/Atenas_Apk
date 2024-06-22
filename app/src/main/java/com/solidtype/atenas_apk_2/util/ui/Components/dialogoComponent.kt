package com.solidtype.atenas_apk_2.util.ui.Components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.Transparente
import com.solidtype.atenas_apk_2.ui.theme.semiTransparente
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun MyDialog(
    mostrar: Boolean,
    onCerrarDialogo: () -> Unit,
    max: Boolean = true,
    sinBoton: Boolean = false,
    clickable: Boolean = false,
    contenido: @Composable () -> Unit
) {
    val noHoverInteractionSource = remember { MutableInteractionSource() }
    AnimatedVisibility (
        visible = mostrar,
        enter = fadeIn(tween(500)),
        exit = fadeOut(tween(500))
    ) {
        BackHandler { onCerrarDialogo() }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = noHoverInteractionSource,
                    indication = null
                ) { if (clickable) onCerrarDialogo() }
                .background(semiTransparente),
            contentAlignment = Alignment.Center
        ) {
            Box( // Fondo
                modifier = if(max) Modifier
                    .width(Pantalla.ancho - 100.dp)
                    .height(Pantalla.alto - 100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xAACCD2E4),
                                Color(0xAA727694),
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .blur(5.dp)
                else
                    Modifier,
            )
            Box( //Contenido
                modifier = if(max) Modifier
                    .width(Pantalla.ancho - 100.dp)
                    .height(Pantalla.alto - 100.dp)
                    .background(Transparente, RoundedCornerShape(16.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xAAFFFFFF),
                        shape = RoundedCornerShape(16.dp)
                    )
                else
                    Modifier
                        .background(Blanco, RoundedCornerShape(16.dp)),
            ) {
                Column(
                    modifier = if (max) Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                    else
                        Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    contenido()
                    if(!sinBoton)
                    Box(
                        modifier = if (max) Modifier
                            .fillMaxWidth()
                        else
                            Modifier,
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Boton("Cerrar") {
                            onCerrarDialogo()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Dialogo(
    titulo: String,
    mostrar: Boolean,
    onCerrarDialogo: () -> Unit,
    max: Boolean = true,
    sinBoton: Boolean = false,
    clickable: Boolean = false,
    content: @Composable () -> Unit
) {
    MyDialog(
        mostrar = mostrar,
        onCerrarDialogo = onCerrarDialogo,
        max = max,
        sinBoton = sinBoton,
        clickable = clickable
    ) {
        Text(
            text = titulo,
            color = AzulGris,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        content()
        Spacer(modifier = Modifier.height(16.dp))
    }
}


//Preview para Vortex T10M (T10MPROPLUS) Horizontal
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun DialogoPreview() {
    //DialogoV2(mostrar = true, onCerrarDialogo = {})
}
