package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

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
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserEvent
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserStatesUI
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UsuariosViewmodel
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components.Detalles
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
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
    uiState: UserStatesUI,
    mostrarRol: MutableState<Boolean>,
    estado: MutableState<String>,
    viewModel: UsuariosViewmodel
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
                uiState,
                mostrarRol,
                estado
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
                            estado.value != "" &&
                            rol.value != ""
                ) {
                    try {
                        if (idUsuario.value.isEmpty() || nombre.value.isEmpty() || apellido.value.isEmpty() || correo.value.isEmpty() || clave.value.isEmpty() || telefono.value.isEmpty() || estado.value.isEmpty() || rol.value.isEmpty()) {
                            throw Exception("Campos vacios.")
                        }

                        if (uiState.usuarios.find { it.id_usuario == idUsuario.value.toLong() } != null) {
                            viewModel.onUserEvent(
                                UserEvent.EditarUsuario(
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
                        } else {
                            viewModel.onUserEvent(
                                UserEvent.AgregarUsuario(
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
                        }

                        idUsuario.value = ""
                        nombre.value = ""
                        apellido.value = ""
                        correo.value = ""
                        clave.value = ""
                        telefono.value = ""
                        estado.value = "Activo"
                    } catch (_: Exception) { }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Boton("Cerrar") {
                    mostrarUsuario.value = false
                }
            }
        }
    }
}