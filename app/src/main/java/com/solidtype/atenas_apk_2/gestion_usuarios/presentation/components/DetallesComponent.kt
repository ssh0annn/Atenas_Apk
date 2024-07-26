package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.ui.Pantalla
import com.solidtype.atenas_apk_2.util.ui.components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.components.InputDetalle

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
    uiRoles: List<com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios>,
    mostrarDialogo: MutableState<Boolean>,
) {
    Column(
        modifier = Modifier
            .padding(top = 0.dp)
            .height(Pantalla.alto * 0.6f)
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
                    Column(
                        modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                    ) {
                        InputDetalle(
                            "Id del Usuario",
                            idUsuario.value,
                            tipo = KeyboardType.Number
                        ) { idUsuario.value = it }
                        InputDetalle("Nombre", nombre.value) { nombre.value = it }
                        InputDetalle("Apellido", apellido.value) {
                            apellido.value = it
                        }
                        InputDetalle(
                            "Correo",
                            correo.value,
                            tipo = KeyboardType.Email
                        ) { correo.value = it }
                        InputDetalle("Clave", clave.value, pass = true) { clave.value = it }
                        InputDetalle(
                            "Teléfono",
                            telefono.value,
                            tipo = KeyboardType.Phone
                        ) {
                            telefono.value = it
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        AutocompleteSelect(
                            "Rol",
                            rol,
                            if (uiRoles.isNotEmpty()) uiRoles.map { it.nombre } else listOf(
                                ""
                            ),
                            onClickAgregar = {
                                mostrarDialogo.value = true
                            },
                        ) { rol.value = it }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}