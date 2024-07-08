package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventariosEvent
import com.solidtype.atenas_apk_2.products.presentation.inventory.ProductosViewStates
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro


@Composable
fun SwitchInactivos(
    uiState: ProductosViewStates,
    viewModel: InventarioViewModel
) {
    Row(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text(
            text = "Mostrar productos inactivos",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = AzulGris,
            modifier = Modifier
                .padding(10.dp)
        )
        Switch(
            checked = uiState.switch,
            colors = SwitchDefaults.colors(
                checkedThumbColor = GrisClaro,
                checkedTrackColor = AzulGris,
                uncheckedThumbColor = AzulGris,
                uncheckedTrackColor = GrisClaro
            ),
            onCheckedChange = {
                viewModel.onEvent(InventariosEvent.Switch)
            }
        )
    }
}