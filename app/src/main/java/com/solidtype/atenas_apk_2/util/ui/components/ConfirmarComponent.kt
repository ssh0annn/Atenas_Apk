package com.solidtype.atenas_apk_2.util.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris

fun (() -> Unit).confirmar(
    mensaje: String,
    showDialog: (Boolean) -> Unit,
    setMessage: (String) -> Unit,
    setAction: (() -> Unit) -> Unit
) {
    setMessage(mensaje)
    setAction(this)
    showDialog(true)
}

@Composable
fun DialogoConfirmacion(
    showDialog: MutableState<Boolean>,
    confirmMessage: MutableState<String>,
    onConfirmAction: MutableState<() -> Unit>
) {
    Dialogo(
        titulo = "Confirma",
        mostrar = showDialog.value,
        onCerrarDialogo = { showDialog.value = false },
        max = false,
        sinBoton = true
    ) {
        Column(
            modifier = Modifier
                .width(400.dp)
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = confirmMessage.value,
                textAlign = TextAlign.Center,
                color = AzulGris,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Boton("Aceptar") {
                    onConfirmAction.value()
                }
                Spacer(modifier = Modifier.width(16.dp))
                Boton("Cancelar") {
                    showDialog.value = false
                }
            }
        }
    }
}