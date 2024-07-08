package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventariosEvent
import com.solidtype.atenas_apk_2.products.presentation.inventory.ProductosViewStates
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.util.ui.Components.Dialogo

@Composable
fun DialogoProducto(
    mostrarProducto: MutableState<Boolean>,
    viewModel: InventarioViewModel,
    categoria: MutableState<String>,
    uiState: ProductosViewStates,
    nombre: MutableState<String>,
    idInventario: MutableState<String>,
    descripcion: MutableState<String>,
    costo: MutableState<String>,
    precio: MutableState<String>,
    modelo: MutableState<String>,
    marca: MutableState<String>,
    cantidad: MutableState<String>,
    idCategoriaText: MutableState<String>,
    idProveedorText: MutableState<String>,
    impuesto: MutableState<String>,
    estado: MutableState<String>,
    context: Context,
    provider: MutableState<String>,
    listEstados: List<String>,
    mostrarCategoria: MutableState<Boolean>,
    mostrarProveedor: MutableState<Boolean>,
    idCategoria: MutableState<String>,
    idProveedor: MutableState<String>
) {
    Dialogo(
        titulo = "Gestor de Productos",
        mostrar = mostrarProducto.value,
        onCerrarDialogo = { mostrarProducto.value = false },
        max = false,
        sinBoton = true
    ) {
        Column(
            modifier = Modifier
                .width(380.dp)
                .padding(16.dp, 16.dp, 16.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Detalles(
                categoria,
                uiState,
                nombre,
                idInventario,
                descripcion,
                costo,
                precio,
                modelo,
                marca,
                cantidad,
                idCategoriaText,
                idProveedorText,
                impuesto,
                estado,
                provider,
                listEstados,
                mostrarCategoria,
                mostrarProveedor
            )
            Row {
                Boton("Guardar") {
                    try {
                        viewModel.onEvent(
                            InventariosEvent.AgregarProductos(
                                inventario(
                                    id_inventario = idInventario.value.toLong(),
                                    id_categoria = idCategoriaText.value.toLong(),
                                    id_proveedor = idProveedorText.value.toLong(),
                                    nombre = nombre.value,
                                    marca = marca.value,
                                    modelo = modelo.value,
                                    cantidad = cantidad.value.toInt(),
                                    precio_compra = costo.value.toDouble(),
                                    precio_venta = precio.value.toDouble(),
                                    impuesto = impuesto.value.toDouble(),
                                    descripcion = descripcion.value,
                                    estado = estado.value == "Activo"
                                )
                            )
                        )
                        idInventario.value = ""
                        idCategoria.value = ""
                        idProveedor.value = ""
                        nombre.value = ""
                        marca.value = ""
                        modelo.value = ""
                        cantidad.value = ""
                        costo.value = ""
                        precio.value = ""
                        impuesto.value = ""
                        descripcion.value = ""
                        estado.value = ""
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "error: campos invalidos",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        Log.e("ErrorInventario", "Error: ${e.message}, Causa: ${e.cause}")
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Boton("Cerrar") {
                    mostrarProducto.value = false
                }
            }
        }
    }
}