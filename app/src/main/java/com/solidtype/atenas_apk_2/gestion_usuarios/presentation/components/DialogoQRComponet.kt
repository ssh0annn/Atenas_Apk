package com.solidtype.atenas_apk_2.gestion_usuarios.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.asImageBitmap
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo
import net.glxn.qrgen.android.QRCode

@Composable
fun DialogoQR(
    mostrarQR: MutableState<Boolean>,
    qrStr: String
) {
    Dialogo(
        titulo = "QR",
        mostrar = mostrarQR.value,
        onCerrarDialogo = { mostrarQR.value = false },
        max = false
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            val qr: Bitmap = QRCode.from(qrStr).withSize(500, 500).bitmap()
            Image(
                bitmap = qr.asImageBitmap(),
                contentDescription = "QR"
            )
        }
    }
}