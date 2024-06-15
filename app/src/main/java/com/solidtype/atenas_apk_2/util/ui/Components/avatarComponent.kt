package com.solidtype.atenas_apk_2.util.ui.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco

@Preview
@Composable
fun Avatar(
    mostrarMenu: MutableState<Boolean> = mutableStateOf(false)
){
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(AzulGris)
            .clickable { mostrarMenu.value = !mostrarMenu.value },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .background(AzulGris),
            tint = Blanco
        )
    }
}