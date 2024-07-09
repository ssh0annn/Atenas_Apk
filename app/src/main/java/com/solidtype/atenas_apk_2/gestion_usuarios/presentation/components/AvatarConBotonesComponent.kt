package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.solidtype.atenas_apk_2.util.ui.components.Boton

@Composable
fun AvatarConBotones(mostrarQR: MutableState<Boolean>) {
    Box(//Avatar y Botones
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) { //Avatar y Botones
        Row {
            Boton("Mostrar QR") {
                mostrarQR.value = true
            }
        }
    }
}