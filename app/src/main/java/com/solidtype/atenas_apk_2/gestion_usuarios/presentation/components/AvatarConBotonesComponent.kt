package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun AvatarConBotones(mostrarQR: MutableState<Boolean>) {
    Row(//Avatar y Botones
        modifier = Modifier.padding(top = 10.dp)
    ) { //Avatar y Botones
        if (true) { // si no hay imagen de perfil
            Avatar()
        } else {
        }
        Spacer(modifier = Modifier.width(Pantalla.ancho - 400.dp))
        Row {
            Boton("Mostrar QR") {
                mostrarQR.value = true
            }
        }
    }
}