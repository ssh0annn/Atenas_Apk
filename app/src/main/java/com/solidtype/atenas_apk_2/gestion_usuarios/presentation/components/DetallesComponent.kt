package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserEvent
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserStatesUI
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UsuariosViewmodel
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.BotonIconCircular
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
import com.solidtype.atenas_apk_2.util.ui.Components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.Components.InputDetalle
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
@OptIn(ExperimentalMultiplatform::class)
fun Detalles(
    idUsuario: MutableState<String>,
    nombre: MutableState<String>,
    apellido: MutableState<String>,
    correo: MutableState<String>,
    clave: MutableState<String>,
    telefono: MutableState<String>,
    rol: MutableState<String>,
    uiState: UserStatesUI,
    mostrarDialogo: MutableState<Boolean>,
    mostrarConfirmar: MutableState<Boolean>,
    estado: MutableState<String>,
    viewModel: UsuariosViewmodel,
    context: Context
) {
    Column(
        modifier = Modifier
            .padding(top = 0.dp)
            .height(Pantalla.alto - 185.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(AzulGris)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp, 10.dp, 10.dp, 5.dp)
                    .fillMaxWidth()
                    .height(Pantalla.alto - 270.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(GrisOscuro)
            ) {// Area de detalles = Imagen del producto, Categoría y Nombre
                item {
                    /*BackHandler(expanded.value) {
                                        expanded.value = false
                                    }*/
                    Column(
                        modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                    ) {
                        InputDetalle("Id del Usuario", idUsuario.value) { idUsuario.value = it }
                        InputDetalle("Nombre", nombre.value) { nombre.value = it }
                        InputDetalle("Apellido", apellido.value) {
                            apellido.value = it
                        }
                        InputDetalle("Correo", correo.value) { correo.value = it }
                        InputDetalle("Clave", clave.value) { clave.value = it }
                        InputDetalle("Teléfono", telefono.value) {
                            telefono.value = it
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        AutocompleteSelect(
                            "Rol",
                            rol.value,
                            if (uiState.roles.isNotEmpty()) uiState.roles.map { it.nombre } else listOf(
                                ""
                            ),
                            onClickAgregar = {
                                mostrarDialogo.value = true
                            },
                        ) { rol.value = it }
                        Spacer(modifier = Modifier.height(10.dp))
                        AutocompleteSelect(
                            "Estado",
                            estado.value,
                            listOf("Activo", "Inactivo")
                        ) { estado.value = it }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {//Botones de cerrar y guardar
                BotonIconCircular(//Eliminar
                    true,
                    onClick = {
                        mostrarConfirmar.value = true
                    }
                )
                Spacer(modifier = Modifier.width(60.dp))
                BotonIconCircular(false, onClick = {
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
                            Toast.makeText(
                                context,
                                "Usuario editado",
                                Toast.LENGTH_LONG
                            ).show()
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
                            Toast.makeText(
                                context,
                                "Usuario guardado",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        idUsuario.value = ""
                        nombre.value = ""
                        apellido.value = ""
                        correo.value = ""
                        clave.value = ""
                        telefono.value = ""
                        estado.value = ""
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "error: ${e.message}",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                })
            }
        }
    }
}