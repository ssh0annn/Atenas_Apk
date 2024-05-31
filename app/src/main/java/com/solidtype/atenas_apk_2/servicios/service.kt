package com.solidtype.atenas_apk_2.servicios

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.solidtype.atenas_apk_2.util.formatearFecha
import com.solidtype.atenas_apk_2.util.ui.Components.DatePickerDialogo
import com.solidtype.atenas_apk_2.util.ui.Components.NavPlato

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun servicios (){

    complementari()

}


@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun InventoryScreenPreview() {
    servicios()
}
