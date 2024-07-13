package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventariosEvent
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo

@Composable
fun DialogoConfirmarProveedor(
    mostrarConfirmarProveedor: MutableState<Boolean>,
    viewModel: InventarioViewModel,
    idProveedor: MutableState<String>,
    nombreProveedor: MutableState<String>,
    tipoDocumentoProveedor: MutableState<String>,
    documentoProveedor: MutableState<String>,
    direccionProveedor: MutableState<String>,
    telefonoProveedor: MutableState<String>,
    emailProveedor: MutableState<String>,
    context: Context
) {
    Dialogo(
        titulo = "Confirma",
        mostrar = mostrarConfirmarProveedor.value,
        onCerrarDialogo = { mostrarConfirmarProveedor.value = false },
        max = false,
        sinBoton = true
    ) {
        Column(
            modifier = Modifier
                .width(400.dp)
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Estás seguro que deseas eliminar este proveedor?",
                textAlign = TextAlign.Center,
                color = AzulGris,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Boton("Aceptar") {
                    try {
                        viewModel.onEvent(
                            InventariosEvent.EliminarProveedor(
                                Personastodas.Proveedor(
                                    id_proveedor = idProveedor.value.toLong(),
                                    nombre = nombreProveedor.value,
                                    tipo_documento = tipoDocumentoProveedor.value,
                                    documento = documentoProveedor.value,
                                    direccion = direccionProveedor.value,
                                    telefono = telefonoProveedor.value,
                                    email = emailProveedor.value
                                )
                            )
                        )

                        viewModel.onEvent(InventariosEvent.Getrpoveedores)

                        idProveedor.value = ""
                        nombreProveedor.value = ""
                        tipoDocumentoProveedor.value = ""
                        documentoProveedor.value = ""
                        direccionProveedor.value = ""
                        telefonoProveedor.value = ""
                        emailProveedor.value = ""

                        mostrarConfirmarProveedor.value = false

                        Toast.makeText(
                            context,
                            "Se eliminó el proveedor",
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "No se pudo eliminar",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Boton("Cancelar") {
                    mostrarConfirmarProveedor.value = false
                }
            }
        }
    }
}