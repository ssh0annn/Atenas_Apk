package com.solidtype.atenas_apk_2.util.ui.Components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun SelecionarFecha(
    text: String,
    data: Long?,
    fechaString: String?,
    derecho: Boolean = false,
    @SuppressLint("ModifierParameter") modifierPadre: Modifier = Modifier,
    modifierHijo: Modifier = Modifier,
    size: Int = -1,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = if (derecho) Alignment.End else Alignment.CenterHorizontally,
        modifier = modifierPadre
    ) {
        BotonBlanco(text = text, onClick = onClick, modifier = modifierHijo, size = size)
            Text(
                if (data == null || data == 0L || fechaString == "") "No seleccionada"
                else SimpleDateFormat("dd/MM/yyyy").format(Date(data)),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
    }
}