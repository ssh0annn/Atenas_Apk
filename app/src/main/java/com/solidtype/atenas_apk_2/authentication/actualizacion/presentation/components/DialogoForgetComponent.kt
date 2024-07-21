package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.components

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.BlancoOpaco
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.components.InputDetalle

@Composable
@OptIn(ExperimentalMultiplatform::class)
fun DialogoForget(
    mostrar: MutableState<Boolean>,
    email: MutableState<String>,
    aceptar: () -> Unit,
    cancelar: () -> Unit
) {
    Dialogo(
        titulo = "Recuperación de Clave",
        mostrar = mostrar.value,
        onCerrarDialogo = {},
        max = false,
        sinBoton = true
    ) {
        Column(
            modifier = Modifier
                .width(400.dp)
                .background(BlancoOpaco, shape = RoundedCornerShape(20))
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Introduzca su correo electrónico para recuperar su contraseña:",
                textAlign = TextAlign.Center,
                color = AzulGris,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputDetalle(label = "Email", valor = email.value) {
                email.value = it
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Boton(
                    "Aceptar",
                    Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
                ) {
                    aceptar()
                }
                Spacer(modifier = Modifier.width(16.dp))
                Boton("Cancelar") {
                    cancelar()
                }
            }
        }
    }
}