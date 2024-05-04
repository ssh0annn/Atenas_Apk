package com.solidtype.atenas_apk_2.facturacion.presentation.componets.Generals

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.FactCheck
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris

@Composable
fun Titulo(){
    Row {//Título
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.FactCheck,
            contentDescription = "",
            tint = AzulGris,
            modifier = Modifier
                .padding(top = 10.dp)
                .size(40.dp)
        )
        Text(
            text = "Facturas",
            color = AzulGris,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )
    }
}