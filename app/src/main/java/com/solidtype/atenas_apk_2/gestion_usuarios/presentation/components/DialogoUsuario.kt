package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo

@Composable
fun DialogoUsuario(
    mostrarUsuario: MutableState<Boolean>,
    idUsuario: MutableState<String>,
    nombre: MutableState<String>,
    apellido: MutableState<String>,
    correo: MutableState<String>,
    clave: MutableState<String>,
    telefono: MutableState<String>,
    rol: MutableState<String>,
    uiRoles: List<roll_usuarios>,
    mostrarRol: MutableState<Boolean>,
    onClickGuardar: () -> Unit
) {
    Dialogo(
        titulo = "Gestor de Usuarios",
        mostrar = mostrarUsuario.value,
        onCerrarDialogo = { mostrarUsuario.value = false },
        max = false,
        sinBoton = true
    ) {
        Column(
            modifier = Modifier
                .width(380.dp)
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Detalles(
                idUsuario,
                nombre,
                apellido,
                correo,
                clave,
                telefono,
                rol,
                uiRoles,
                mostrarRol
            )
            Row {
                Boton(
                    "Guardar",
                    idUsuario.value .matches("[0-9]+".toRegex()) &&
                            nombre.value != "" &&
                            apellido.value != "" &&
                            Patterns.EMAIL_ADDRESS.matcher(correo.value).matches() &&
                            (clave.value.length in 8..16) &&
                            telefono.value.matches("8\\d9\\d{7}".toRegex()) &&
                            //estado.value != "" &&
                            rol.value != ""
                ) {
                    onClickGuardar()
                }
                Spacer(modifier = Modifier.width(16.dp))
                Boton("Cerrar") {
                    mostrarUsuario.value = false
                }
            }
        }
    }
}