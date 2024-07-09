package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserEvent
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserStatesUI
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UsuariosViewmodel
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
import com.solidtype.atenas_apk_2.util.ui.components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.components.BotonBlanco
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.components.InputDetalle

@Composable
@OptIn(ExperimentalMultiplatform::class)
fun DialogoSimple(
    mostrarDialogo: MutableState<Boolean>,
    mostrarConfirmarRol: MutableState<Boolean>,
    idRollUsuario: MutableState<String>,
    nombreRollUsuario: MutableState<String>,
    descripcion: MutableState<String>,
    estadoRollUsuario: MutableState<String>,
    uiState: UserStatesUI,
    viewModel: UsuariosViewmodel,
    context: Context
) {
    Dialogo("Gestor de Roles", mostrarDialogo.value, { mostrarDialogo.value = false }, false) {
        Row {//Detalles y Lista
            Column( //Detalles
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .background(AzulGris, RoundedCornerShape(20.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(GrisOscuro, RoundedCornerShape(5.dp))
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            InputDetalle("ID", idRollUsuario.value) { idRollUsuario.value = it }
                            InputDetalle(
                                "Rol",
                                nombreRollUsuario.value
                            ) { nombreRollUsuario.value = it }
                            InputDetalle("Descripción", descripcion.value) {
                                descripcion.value = it
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            AutocompleteSelect(
                                "Estado",
                                estadoRollUsuario.value,
                                listOf("Activo", "Inactivo")
                            ) { estadoRollUsuario.value = it }
                        }
                    }
                }
                Row{
                    BotonBlanco("Guardar") {
                        try {
                            if (idRollUsuario.value.isEmpty() || nombreRollUsuario.value.isEmpty() || descripcion.value.isEmpty() || estadoRollUsuario.value.isEmpty()) {
                                throw Exception("Campos vacios.")
                            }

                            if (uiState.roles.find { it.id_roll_usuario == idRollUsuario.value.toLong() } != null) {
//                            Log.i("GestionUsuariosScreen", "Editar Rol")
                                viewModel.onUserEvent(
                                    UserEvent.EditarRol(
                                        roll_usuarios(
                                            id_roll_usuario = idRollUsuario.value.toLong(),
                                            nombre = nombreRollUsuario.value,
                                            descripcion = descripcion.value,
                                            estado = estadoRollUsuario.value.formatoActivoDDBB()
                                        )
                                    )
                                )
                                //Error: No se puede editar un rol
                                //throw Exception("No se puede editar un rol.")
                            } else {
                                viewModel.onUserEvent(
                                    UserEvent.AgregarNuevoRol(
                                        roll_usuarios(
                                            id_roll_usuario = idRollUsuario.value.toLong(),
                                            nombre = nombreRollUsuario.value,
                                            descripcion = descripcion.value,
                                            estado = estadoRollUsuario.value.formatoActivoDDBB()
                                        )
                                    )
                                )
                            }

                            idRollUsuario.value = ""
                            nombreRollUsuario.value = ""
                            descripcion.value = ""
                            estadoRollUsuario.value = ""

                            Toast.makeText(
                                context,
                                "Rol guardado",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "error: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    BotonBlanco("Eliminar"){ mostrarConfirmarRol.value = true }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Column( //Lista
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .background(AzulGris, RoundedCornerShape(20.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = 355.dp)
                        .padding(10.dp)
                        .background(GrisOscuro, RoundedCornerShape(5.dp))
                ) {
                    item {
                        Column(
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .background(GrisOscuro, RoundedCornerShape(5.dp))
                            ) {
                                Text(
                                    text = "ID",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Nombre",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Descripción",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Estado",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    if (uiState.roles.isNotEmpty())
                        items(uiState.roles) { rol ->
                            Row(
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp,
                                        end = 5.dp,
                                        top = 5.dp,
                                        bottom = 5.dp
                                    )
                                    .background(
                                        GrisClaro,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        idRollUsuario.value = rol.id_roll_usuario.toString()
                                        nombreRollUsuario.value = rol.nombre
                                        descripcion.value = rol.descripcion
                                        estadoRollUsuario.value = rol.estado.formatoActivo()
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = rol.id_roll_usuario.toString(),
                                    modifier = Modifier
                                        .padding(5.dp, 5.dp, 0.dp, 5.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = rol.nombre,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = rol.descripcion,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = rol.estado.formatoActivo(),
                                    modifier = Modifier
                                        .padding(0.dp, 5.dp, 5.dp, 5.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                }
            }
        }
    }
}