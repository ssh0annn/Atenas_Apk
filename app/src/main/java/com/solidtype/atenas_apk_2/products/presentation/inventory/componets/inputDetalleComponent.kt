package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import android.graphics.Color.parseColor
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMultiplatform
@Composable
fun InputDetalle(
    label: String,
    corto: Boolean = false,
    valor: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 5.dp),
    ) {
        TextField(
            value = valor,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .width(
                    when (corto) {
                        true -> 250.dp
                        false -> 300.dp

                    }
                )
                // .background(Color(android.graphics.Color.parseColor("#FFFFFF")))
                .background(Color(0xFFFFFFFF), RoundedCornerShape(15.dp))
                // .background(Color.White)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp)),
            // .border(width = 2.dp, color = Color.Gray),
            label = {
                Text(
                    text = label,
                    color = Color(parseColor("#343341")),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
            )
        )
    }
}

@OptIn(ExperimentalMultiplatform::class)
@Preview(backgroundColor = 0xFF888888, widthDp = 360, heightDp = 640, showBackground = true)
@Composable
fun PruebaInput() {
    var valor by remember {
        mutableStateOf("")
    }
    InputDetalle("Texto", false, "") { valor = it }
}