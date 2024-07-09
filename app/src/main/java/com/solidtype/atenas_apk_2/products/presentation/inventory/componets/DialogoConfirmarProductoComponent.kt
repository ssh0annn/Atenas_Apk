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
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventariosEvent
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo

@Composable
fun DialogoConfirmarProducto(
    mostrarConfirmarProducto: MutableState<Boolean>,
    viewModel: InventarioViewModel,
    idInventario: MutableState<String>,
    idCategoriaText: MutableState<String>,
    idProveedorText: MutableState<String>,
    nombre: MutableState<String>,
    marca: MutableState<String>,
    modelo: MutableState<String>,
    cantidad: MutableState<String>,
    costo: MutableState<String>,
    precio: MutableState<String>,
    impuesto: MutableState<String>,
    descripcion: MutableState<String>,
    estado: MutableState<String>,
    categoria: MutableState<String>,
    provider: MutableState<String>,
    context: Context,
    mostrarConfirmarCategoria: MutableState<Boolean>
) {
    Dialogo(
        titulo = "Confirma",
        mostrar = mostrarConfirmarProducto.value,
        onCerrarDialogo = { mostrarConfirmarProducto.value = false },
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
                text = "¿Estás seguro que deseas eliminar este producto?",
                textAlign = TextAlign.Center,
                color = AzulGris,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Boton("Aceptar") {
                    try {
                        viewModel.onEvent(
                            InventariosEvent.EliminarProductos(
                                inventario(
                                    idInventario.value.toLong(),
                                    idCategoriaText.value.toLong(),
                                    idProveedorText.value.toLong(),
                                    nombre.value,
                                    marca.value,
                                    modelo.value,
                                    cantidad.value.toInt(),
                                    costo.value.toDouble(),
                                    precio.value.toDouble(),
                                    impuesto.value.toDouble(),
                                    descripcion.value,
                                    estado.value.formatoActivoDDBB()
                                )
                            )
                        )

                        idInventario.value = ""
                        categoria.value = ""
                        nombre.value = ""
                        descripcion.value = ""
                        costo.value = ""
                        precio.value = ""
                        modelo.value = ""
                        marca.value = ""
                        cantidad.value = ""
                        impuesto.value = ""
                        estado.value = ""
                        provider.value = ""

                        mostrarConfirmarProducto.value = false

                        Toast.makeText(
                            context,
                            "Se eliminó el producto",
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
                    mostrarConfirmarCategoria.value = false
                }
            }
        }
    }
}