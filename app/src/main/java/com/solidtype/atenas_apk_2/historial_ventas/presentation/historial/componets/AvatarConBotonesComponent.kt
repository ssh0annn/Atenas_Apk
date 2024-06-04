package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton

@Composable
fun AvatarConBotones(
    viewModel: HistorailViewModel,
    fechaIni: MutableState<String>,
    fechaFin: MutableState<String>,
    context: Context,
    showSnackbarIni: MutableState<Boolean>
) {
    Row {
        //Avatar
        if (true) { // si no hay imagen de perfil
            Avatar()
        } else {//Mostrar foto de perfil
            //Image(painter = , contentDescription = )
        }
        Spacer(modifier = Modifier.width(400.dp))
        Row {
            Boton("Ver Todo") {
                viewModel.MostrarHistoriar()
                viewModel.mostrarTicket()
                // limpiar fechas
                fechaIni.value = ""
                fechaFin.value = ""
            }
            Boton("Exportar") {
                Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
                    .show()
                viewModel.Exportar()
                showSnackbarIni.value = true
            }
        }
    }
}