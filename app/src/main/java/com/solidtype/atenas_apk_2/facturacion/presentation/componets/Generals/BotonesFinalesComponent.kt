package com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.solidtype.atenas_apk_2.facturacion.presentation.FacturaViewModel
import com.solidtype.atenas_apk_2.util.fomatoLocalDate
import com.solidtype.atenas_apk_2.util.ui.Components.Boton

@Composable
fun BotonesFinales(
    viewModel: FacturaViewModel,
    fechaFin: MutableState<String>,
    fechaIni: MutableState<String>,
    noFacturaCliente: MutableState<String>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Boton("Ver Todos") {
            viewModel.mostrarFactura()
            fechaFin.value = ""
            fechaIni.value = ""
        }
        Boton("Filtrar", fechaFin.value.isNotEmpty() && fechaIni.value.isNotEmpty()) {
            viewModel.buscarfacturas(
                fechaIni.value.fomatoLocalDate(),
                fechaFin.value.fomatoLocalDate(),
                noFacturaCliente.value
            )
        }
    }
}