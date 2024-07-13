package com.solidtype.atenas_apk_2.util.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco

@ExperimentalMultiplatform
@Composable
fun InputDetalle(
    label: String,
    valor: String,
    corto: Boolean = false,
    pass: Boolean = false,
    onValueChange: (String) -> Unit
) {
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier.padding(vertical = 5.dp),
    ) {
        TextField(
            value = valor,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = AzulGris,
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
                .background(Blanco, RoundedCornerShape(15.dp))
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp)),
            label = {
                Text(
                    text = label,
                    color = AzulGris,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Blanco,
                unfocusedBorderColor = Blanco,
            ),
            trailingIcon = {
                if (pass) {
                    IconButton(
                        onClick = { passwordVisible.value = !passwordVisible.value }
                    ) {
                        Image(
                            imageVector = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            },
            visualTransformation =
                if (pass)
                    if (passwordVisible.value) VisualTransformation.None
                    else PasswordVisualTransformation()
                else VisualTransformation.None,
            keyboardOptions =
                if (pass) KeyboardOptions(keyboardType = KeyboardType.Password)
                else KeyboardOptions.Default
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
    InputDetalle("Texto", "") { valor = it }
}