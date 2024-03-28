package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.solidtype.atenas_apk_2.Authentication.presentation.login.component.Container
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showSystemUi = true)
@Composable
fun BusquedaBarra(){
    OutlinedTextField(
        value = "",
        onValueChange = {" "},
        modifier= Modifier.padding(24.dp),
        label= {Text("Buscar productos...")}
       // ,colors= TextFiledDefaults.

    )
}