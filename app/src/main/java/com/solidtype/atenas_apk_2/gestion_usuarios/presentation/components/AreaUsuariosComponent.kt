package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserStatesUI
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun AreaUsuarios(
    uiState: UserStatesUI,
    idUsuario: MutableState<String>,
    nombre: MutableState<String>,
    apellido: MutableState<String>,
    correo: MutableState<String>,
    clave: MutableState<String>,
    telefono: MutableState<String>,
    estado: MutableState<String>,
    rol: MutableState<String>,
    mostrarUsuario: MutableState<Boolean>,
    editar: MutableState<Boolean>,
    mostrarConfirmarUsuario: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .padding(start = 20.dp)
            .fillMaxWidth()
            .height(Pantalla.alto - 250.dp)
            .background(AzulGris, RoundedCornerShape(20.dp))
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(20.dp))
                .background(AzulGris)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(5.dp))
                    .background(GrisOscuro)
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .background(GrisOscuro)

                ) {
                    Text(
                        text = "Imagen",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    ) // Aquí debería ir la imagen del producto
                    Text(
                        text = "Nombre",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Correo",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Teléfono",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Rol",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "",
                        modifier = Modifier.weight(0.5f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 0.dp, end = 10.dp, bottom = 10.dp)
                        .fillMaxSize()
                        .background(GrisOscuro, RoundedCornerShape(5.dp))
                ) { //buscar componente para agregar filas de cards
                    if (uiState.usuarios.isNotEmpty())
                        items(uiState.usuarios) { usuario ->
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
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .weight(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.AccountCircle,
                                        contentDescription = null,
                                        tint = AzulGris,
                                        modifier = Modifier
                                            .size(50.dp)
                                    )
                                }// Aquí debería ir la imagen del Usuario
                                Text(
                                    text = usuario.nombre,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = usuario.email,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = usuario.telefono,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = uiState.roles.find { it.id_roll_usuario == usuario.id_roll_usuario }?.nombre
                                        ?: "",
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                //Iconos de editar y eliminar
                                Row(
                                    modifier = Modifier.weight(0.5f),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    IconButton(onClick = {
                                        mostrarUsuario.value = true
                                        editar.value = true

                                        idUsuario.value = usuario.id_usuario.toString()
                                        nombre.value = usuario.nombre
                                        apellido.value = usuario.apellido
                                        correo.value = usuario.email
                                        clave.value = usuario.clave
                                        telefono.value = usuario.telefono
                                        estado.value = usuario.estado.formatoActivo()
                                        //filtro
                                        rol.value =
                                            uiState.roles.find { it.id_roll_usuario == usuario.id_roll_usuario }?.nombre ?: ""
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Edit,
                                            contentDescription = null,
                                            tint = AzulGris
                                        )
                                    }
                                    if (usuario.estado)
                                        IconButton(onClick = {
                                            mostrarConfirmarUsuario.value = true

                                            idUsuario.value = usuario.id_usuario.toString()
                                            nombre.value = usuario.nombre
                                            apellido.value = usuario.apellido
                                            correo.value = usuario.email
                                            clave.value = usuario.clave
                                            telefono.value = usuario.telefono
                                            estado.value = usuario.estado.formatoActivo()
                                            //filtro
                                            rol.value =
                                                uiState.roles.find { it.id_roll_usuario == usuario.id_roll_usuario }?.nombre ?: ""
                                        }) {
                                            Icon(
                                                imageVector = Icons.Filled.Delete,
                                                contentDescription = null,
                                                tint = AzulGris
                                            )
                                        }
                                }
                            }
                        }
                }
            }
        }
    }
}