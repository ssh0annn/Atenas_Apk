package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun bottonAddClient(
    modifier:Modifier,
    onFabClicked: () -> Unit = { }
){
    FloatingActionButton(
       onClick = { onFabClicked},
        modifier = modifier
            .height(52.dp)
            .widthIn(min = 52.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "Add")

    }

}