package com.solidtype.atenas_apk_2.servicios

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.solidtype.atenas_apk_2.ui.theme.GrisAzulado
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.ui.theme.Negro
import com.solidtype.atenas_apk_2.ui.theme.PurpleGrey40

@ExperimentalMultiplatform
@Composable
fun Input(
    label: String,
    valor: String,
    derecho: Boolean = false,
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
                color = Negro ,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .width(240.dp)
                .background(Blanco, RoundedCornerShape(20.dp))
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp)),

            label = {
                Text(
                    text = label,
                    color = Negro,
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

@ExperimentalMultiplatform
@Composable
fun Inputlargo(
    label: String,
    valor: String,
    derecho: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(
    ) {
        TextField(
            value = valor,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Negro,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(130.dp)
                .background(
                    Blanco,
                    RoundedCornerShape(20.dp))
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp)),

            label = {
                Text(
                    text = label,
                    color = Negro,
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


@ExperimentalMultiplatform
@Composable
fun Inputmed(
    label: String,
    valor: String,
    derecho: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(


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
                .width(280.dp)
                .padding(8.dp)
                .height(60.dp)
                .background(Blanco,
                    RoundedCornerShape(20.dp))
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp)),

            label = {
                Text(
                    text = label,
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


@ExperimentalMultiplatform
@Composable
fun Inputt(
    label: String,
    valor: String,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(


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
                .width(190.dp)
                .padding(8.dp)
                .height(60.dp)
                .background(Blanco,
                    RoundedCornerShape(20.dp))
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp)),

            label = {
                Text(
                    text = label,
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


@ExperimentalMultiplatform
@Composable
fun Inputpeq(
    label: String,
    valor: String,
    derecho: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(


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
                .width(90.dp)
                .padding(8.dp)
                .height(60.dp)
                .background(Blanco,
                    RoundedCornerShape(20.dp))
                .wrapContentHeight()
                .clip(RoundedCornerShape(15.dp)),

            label = {
                Text(
                    text = label,
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