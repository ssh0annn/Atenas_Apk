package com.solidtype.atenas_apk_2.util.ui.Components

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris

@Composable
fun Titulo(
    text: String,
    imageVector: ImageVector? = null
) {
    Row {
        if (imageVector != null)
            Icon(
                imageVector = imageVector, // imageVector = Icons.AutoMirrored.Outlined.FactCheck en un Screen e igual a otro en otro Screen
                contentDescription = "",
                tint = AzulGris,
                modifier = Modifier
                    .padding(top = 10.dp, end = 5.dp)
                    .size(40.dp)
            )
        Text(
            text = text,
            color = AzulGris,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top=10.dp)
        )
    }
}