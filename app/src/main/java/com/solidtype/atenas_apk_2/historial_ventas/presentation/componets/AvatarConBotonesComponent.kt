package com.solidtype.atenas_apk_2.historial_ventas.presentation.componets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.solidtype.atenas_apk_2.historial_ventas.presentation.HistorailViewModel
import com.solidtype.atenas_apk_2.util.ui.components.Boton

@Composable
fun AvatarConBotones(
    viewModel: HistorailViewModel,
    fechaIni: MutableState<String>,
    fechaFin: MutableState<String>,
    context: Context,
    showSnackbarIni: MutableState<Boolean>
) {
    Box(//Avatar y Botones
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) { //Avatar y Botones
        Row {
            Boton("Ver Todo") {
//                viewModel.MostrarHistoriar()
//                viewModel.mostrarTicket()
                // limpiar fechas
                fechaIni.value = ""
                fechaFin.value = ""
            }
            Boton("Exportar") {
                Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
                    .show()
               // viewModel.Exportar()
                showSnackbarIni.value = true
            }
        }
    }
}