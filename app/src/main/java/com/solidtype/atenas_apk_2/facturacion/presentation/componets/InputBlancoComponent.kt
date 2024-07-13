package com.solidtype.atenas_apk_2.facturacion.presentation.componets

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco

@ExperimentalMultiplatform
@Composable
fun InputBlanco(
    label: String = "",
    placeholder: String = "",
    valor: String,
    derecho: Boolean = false,
    largo: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier.padding(vertical = 5.dp),
        contentAlignment = when (!derecho) {
            true -> Alignment.CenterStart
            false -> Alignment.CenterEnd
        }
    ) {
        TextField(
            value = valor,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .width(if (largo) 320.dp else 200.dp)
                .background(Blanco, RoundedCornerShape(15.dp))
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp))
                .border(
                    width = 2.dp,
                    color = AzulGris,
                    shape = RoundedCornerShape(15.dp)
                ),
            label = if (label.isNotEmpty()) {
                {
                    Text(
                        text = label,
                        color = AzulGris,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            } else {
                null
            },
            placeholder = {
                Text(
                    text = placeholder,
                    color = AzulGris,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Blanco,
                unfocusedBorderColor = Blanco,
            )
        )
    }
}