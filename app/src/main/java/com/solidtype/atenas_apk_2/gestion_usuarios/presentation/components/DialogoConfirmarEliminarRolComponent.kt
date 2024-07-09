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
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UserEvent
import com.solidtype.atenas_apk_2.gestion_usuarios.presentation.UsuariosViewmodel
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo

@Composable
fun DialogoConfirmarEliminarRol(
    mostrarConfirmarRol: MutableState<Boolean>,
    viewModel: UsuariosViewmodel,
    idRollUsuario: MutableState<String>,
    nombreRollUsuario: MutableState<String>,
    descripcion: MutableState<String>,
    estadoRollUsuario: MutableState<String>,
    context: Context
) {
    Dialogo(
        titulo = "Confirma",
        mostrar = mostrarConfirmarRol.value,
        onCerrarDialogo = { mostrarConfirmarRol.value = false },
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
                text = "¿Estás seguro que deseas eliminar este rol?",
                textAlign = TextAlign.Center,
                color = AzulGris,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Boton("Aceptar") {
                    try {
                        viewModel.onUserEvent(
                            UserEvent.ElimnarRoll(
                                roll_usuarios(
                                    idRollUsuario.value.toLong(),
                                    nombreRollUsuario.value,
                                    descripcion.value,
                                    estadoRollUsuario.value.formatoActivoDDBB()
                                )
                            )
                        )

                        idRollUsuario.value = ""
                        nombreRollUsuario.value = ""
                        descripcion.value = ""
                        estadoRollUsuario.value = ""

                        mostrarConfirmarRol.value = false

                        Toast.makeText(
                            context,
                            "Se eliminó el rol",
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
                    mostrarConfirmarRol.value = false
                }
            }
        }
    }
}