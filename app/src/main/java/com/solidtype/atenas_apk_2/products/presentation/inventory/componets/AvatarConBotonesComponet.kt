package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import com.solidtype.atenas_apk_2.util.showFilePicker
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton

@Composable
fun AvatarConBotones(
    context: Context,
    viewModel: InventarioViewModel,
    showSnackbarIni: MutableState<Boolean>,
    mostrar: MutableState<Boolean>
) {
    Box(//Avatar y Botones
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) { //Avatar y Botones
        Row {
            //Botones para Importar, Exportar y Ver
            Boton("Importar") {
                showFilePicker(context)
            }
            Boton("Exportar") {
                Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
                    .show()
                viewModel.exportarExcel()
                showSnackbarIni.value = true
            }
            Boton("Ejemplar") {
                mostrar.value = true
            }
        }
    }
}