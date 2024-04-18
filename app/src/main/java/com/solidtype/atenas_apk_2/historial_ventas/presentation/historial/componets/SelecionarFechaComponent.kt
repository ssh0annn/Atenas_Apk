package com.solidtype.atenas_apk_2.historial_ventas.presentation.historial.componets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun SelecionarFecha(text: String, data: Long?, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BotonBlanco(text = text, onClick = onClick)
        data?.let {
            Text(
                SimpleDateFormat("dd/MM/yyyy").format(Date(it)),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}