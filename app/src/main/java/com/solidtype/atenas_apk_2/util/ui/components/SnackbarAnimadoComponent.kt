package com.solidtype.atenas_apk_2.util.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.RojoPalido
import com.solidtype.atenas_apk_2.ui.theme.VerdePalido

@Composable
fun SnackbarAnimado(
    showSnackbar: Boolean,
    // uiState: HistorialUIState o ProductosViewStates
    uriPath: Uri?,
    context: Context
) {
    AnimatedVisibility(
        visible = showSnackbar,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(500)
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Snackbar(
                action = {
                    if (uriPath != null) {
                        Row {
                            BotonBlanco("Compartir") {
                                val fileUri =uriPath
                                val shareIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_STREAM, fileUri)
                                    type =
                                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                }
                                val chooser =
                                    Intent.createChooser(shareIntent, "Compartir archivo")
                                context.startActivity(chooser)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            BotonBlanco("Ver") {
                                val fileUri = uriPath
                                val openIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_VIEW
                                    setDataAndType(
                                        fileUri,
                                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                                    )
                                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                                }
                                val chooser = Intent.createChooser(openIntent, "Abrir archivo")
                                if (openIntent.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(chooser)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "No se encontró una aplicación para abrir este archivo. Favor de instalar una aplicación compatible con archivos de Excel.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                },
                modifier = Modifier.padding(8.dp),
                containerColor = AzulGris
            ) {
                Text(
                    text = if (uriPath != null) "El archivo se guardó en: ${uriPath}" else "Hubo un error al exportar",
                    color = if (uriPath != null) VerdePalido else RojoPalido
                )
            }
        }
    }
}