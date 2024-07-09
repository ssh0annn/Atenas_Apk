package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import android.content.Context
import android.widget.Toast
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
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserEvent
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserStatesUI
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UsuariosViewmodel
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo

@Composable
fun DialogoConfirmarEliminarUsuario(
    mostrarConfirmar: MutableState<Boolean>,
    viewModel: UsuariosViewmodel,
    idUsuario: MutableState<String>,
    uiState: UserStatesUI,
    rol: MutableState<String>,
    nombre: MutableState<String>,
    apellido: MutableState<String>,
    correo: MutableState<String>,
    clave: MutableState<String>,
    telefono: MutableState<String>,
    estado: MutableState<String>,
    context: Context
) {
    Dialogo(
        titulo = "Confirma",
        mostrar = mostrarConfirmar.value,
        onCerrarDialogo = { mostrarConfirmar.value = false },
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
                text = "¿Estás seguro que deseas eliminar este usuario?",
                textAlign = TextAlign.Center,
                color = AzulGris,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Boton("Aceptar") {
                    try {
                        viewModel.onUserEvent(
                            UserEvent.BorrarUsuario(
                                usuario(
                                    id_usuario = idUsuario.value.toLong(),
                                    id_roll_usuario = uiState.roles.find { it.nombre == rol.value }?.id_roll_usuario
                                        ?: 0,
                                    nombre = nombre.value,
                                    apellido = apellido.value,
                                    email = correo.value,
                                    clave = clave.value,
                                    telefono = telefono.value,
                                    estado = estado.value.formatoActivoDDBB()
                                )
                            )
                        )
                        idUsuario.value = ""
                        nombre.value = ""
                        apellido.value = ""
                        correo.value = ""
                        clave.value = ""
                        telefono.value = ""
                        estado.value = ""

                        mostrarConfirmar.value = false

                        Toast.makeText(
                            context,
                            "Se eliminó el usuario",
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "No se pudo eliminar",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Boton("Cancelar") {
                    mostrarConfirmar.value = false
                }
            }
        }
    }
}