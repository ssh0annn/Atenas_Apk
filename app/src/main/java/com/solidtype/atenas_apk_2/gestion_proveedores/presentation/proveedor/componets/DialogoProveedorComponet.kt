package com.solidtype.atenas_apk_2.gestion_proveedores.presentation.proveedor.componets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventariosEvent
import com.solidtype.atenas_apk_2.products.presentation.inventory.ProductosViewStates
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.ui.components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.components.BotonBlanco
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo
import com.solidtype.atenas_apk_2.util.ui.components.InputDetalle
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
@OptIn(ExperimentalMultiplatform::class)
fun DialogoProveedor(
    mostrarProveedor: MutableState<Boolean>,
    nombreProveedor: MutableState<String>,
    tipoDocumentoProveedor: MutableState<String>,
    documentoProveedor: MutableState<String>,
    direccionProveedor: MutableState<String>,
    telefonoProveedor: MutableState<String>,
    emailProveedor: MutableState<String>,
    viewModel: InventarioViewModel,
    context: Context,
    uiState: ProductosViewStates,
    mostrarConfirmarProveedor: MutableState<Boolean>,
    idProveedor: MutableState<String>
) {
    Dialogo(
        "Gestor de Proveedores",
        mostrarProveedor.value,
        { mostrarProveedor.value = false },
        false
    ) {
        Row {//Detalles y Lista
            Column( //Detalles
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .background(AzulGris, RoundedCornerShape(20.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(GrisOscuro, RoundedCornerShape(5.dp))
                        .height(Pantalla.alto * 0.45f)
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            InputDetalle(
                                "Proveedor",
                                nombreProveedor.value
                            ) { nombreProveedor.value = it }
                            Spacer(modifier = Modifier.height(5.dp))
                            AutocompleteSelect(
                                text = "Tipo de Documento",
                                variableStr = tipoDocumentoProveedor.value,
                                items = listOf("Cédula", "Pasaporte")
                            ) {
                                tipoDocumentoProveedor.value = it
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            InputDetalle("Documento", documentoProveedor.value) {
                                documentoProveedor.value = it
                            }
                            InputDetalle("Dirección", direccionProveedor.value) {
                                direccionProveedor.value = it
                            }
                            InputDetalle("Teléfono", telefonoProveedor.value) {
                                telefonoProveedor.value = it
                            }
                            InputDetalle("Email", emailProveedor.value) {
                                emailProveedor.value = it
                            }
                            /*Spacer(modifier = Modifier.height(5.dp))
                                AutocompleteSelect(
                                    "Estado",
                                    estadoProveedor.value,
                                    listOf("Activo", "Inactivo")
                                ) { estadoProveedor.value = it }*/
                        }
                    }
                }
                Row {
                    BotonBlanco("Guardar") {
                        try {
                            if (nombreProveedor.value.isEmpty() || tipoDocumentoProveedor.value.isEmpty() || documentoProveedor.value.isEmpty() || direccionProveedor.value.isEmpty() || telefonoProveedor.value.isEmpty() || emailProveedor.value.isEmpty()) {
                                throw Exception("Campos vacios.")
                            }

                            viewModel.onEvent(
                                InventariosEvent.CrearProveedor(
                                    Personastodas.Proveedor(
                                        id_proveedor = 0,
                                        nombre = nombreProveedor.value,
                                        tipo_documento = tipoDocumentoProveedor.value,
                                        documento = documentoProveedor.value,
                                        direccion = direccionProveedor.value,
                                        telefono = telefonoProveedor.value,
                                        email = emailProveedor.value
                                    )
                                )
                            )

                            nombreProveedor.value = ""
                            tipoDocumentoProveedor.value = ""
                            documentoProveedor.value = ""
                            direccionProveedor.value = ""
                            telefonoProveedor.value = ""
                            emailProveedor.value = ""

                            Toast.makeText(
                                context,
                                "Proveedor guardado",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "error: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                        BotonBlanco("Eliminar") { mostrarConfirmarProveedor.value = true }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            Column( //Lista
                modifier = Modifier
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
                    .background(AzulGris, RoundedCornerShape(20.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .height(Pantalla.alto * 0.557f)
                        .padding(10.dp)
                        .background(GrisOscuro, RoundedCornerShape(5.dp))
                ) {
                    item {
                        Column(
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .background(GrisOscuro, RoundedCornerShape(5.dp))
                            ) {
                                Text(
                                    text = "ID",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Nombre",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Documento",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Teléfono",
                                    modifier = Modifier.weight(1f),
                                    color = Blanco,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    if (uiState.proveedores.isNotEmpty())
                        items(uiState.proveedores) { proveedor ->
                            Row(
                                modifier = Modifier
                                    .padding(
                                        start = 10.dp,
                                        end = 5.dp,
                                        top = 5.dp,
                                        bottom = 5.dp
                                    )
                                    .background(
                                        GrisClaro,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        idProveedor.value = proveedor.id_proveedor.toString()
                                        nombreProveedor.value = proveedor.nombre.toString()
                                        tipoDocumentoProveedor.value =
                                            proveedor.tipo_documento.toString()
                                        documentoProveedor.value = proveedor.documento.toString()
                                        direccionProveedor.value = proveedor.direccion.toString()
                                        telefonoProveedor.value = proveedor.telefono.toString()
                                        emailProveedor.value = proveedor.email.toString()
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = proveedor.id_proveedor.toString(),
                                    modifier = Modifier
                                        .padding(5.dp, 5.dp, 0.dp, 5.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = proveedor.nombre.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = proveedor.documento.toString(),
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = proveedor.telefono.toString(),
                                    modifier = Modifier
                                        .padding(0.dp, 5.dp, 5.dp, 5.dp)
                                        .weight(1f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                }
            }
        }
    }
}