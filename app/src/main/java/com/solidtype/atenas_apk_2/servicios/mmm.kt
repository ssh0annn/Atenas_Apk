package com.solidtype.atenas_apk_2.servicios

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun alertDialog() {

    var mostrar = false;

    Box (modifier = Modifier
        .width(1080.dp)
    ){
        Box (modifier = Modifier .fillMaxWidth()){
            AlertDialog(
                onDismissRequest = { /* openDialog.value = false */ },
                modifier = Modifier.width(1000.dp),
                text = { if (mostrar == false ) {cuerpo() } else { cuerpo2() }},
                confirmButton = {
                    TextButton(onClick = { /* Confirm action */ }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { /* Dismiss action */ }) {
                        Text("Cancel")
                    }
                }
            )

        }
    }
}

//@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
//@Composable
//fun InventoryScreenPreview() {
//    alertDialog()
//}