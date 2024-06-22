package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Pantalla

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