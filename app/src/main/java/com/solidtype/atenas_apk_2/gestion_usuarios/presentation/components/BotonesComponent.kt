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
fun Botones(
    mostrarQR: MutableState<Boolean>,
    mostrarUsuario: MutableState<Boolean>,
    idUsuario: MutableState<String>,
    nombre: MutableState<String>,
    apellido: MutableState<String>,
    correo: MutableState<String>,
    clave: MutableState<String>,
    telefono: MutableState<String>,
    //estado: MutableState<String>,
    rol: MutableState<String>,
    editar: MutableState<Boolean>
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Row {
            Boton("Mostrar QR") {
                mostrarQR.value = true
            }
            Boton("Agregar"){
                mostrarUsuario.value = true
                editar.value = false

                idUsuario.value = ""
                nombre.value = ""
                apellido.value = ""
                correo.value = ""
                clave.value = ""
                telefono.value = ""
                //estado.value = "Activo"
                rol.value = ""
            }
        }
    }
}