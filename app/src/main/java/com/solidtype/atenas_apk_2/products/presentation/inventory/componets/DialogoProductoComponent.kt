package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

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
import com.solidtype.atenas_apk_2.util.ui.components.Boton
import com.solidtype.atenas_apk_2.util.ui.components.Dialogo

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
    //estado: MutableState<String>,
    provider: MutableState<String>,
    //listEstados: List<String>,
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
                provider,
                mostrarCategoria,
                mostrarProveedor
            )
            Row {
                Boton(
                    "Guardar",
                    idInventario.value.matches("[0-9]+".toRegex()) &&
                            idCategoriaText.value.matches("[0-9]+".toRegex()) &&
                            idProveedorText.value.matches("[0-9]+".toRegex()) &&
                            nombre.value != "" &&
                            marca.value != "" &&
                            modelo.value != "" &&
                            cantidad.value.matches("[0-9]+".toRegex()) &&
                            (costo.value.matches("[0-9]+".toRegex()) || costo.value.matches("[0-9]+.[0-9]+".toRegex())) &&
                            (precio.value.matches("[0-9]+".toRegex()) || precio.value.matches("[0-9]+.[0-9]+".toRegex())) &&
                            (impuesto.value.matches("[0-9]+".toRegex()) || impuesto.value.matches("[0-9]+.[0-9]+".toRegex())) &&
                            descripcion.value != ""
                ) {
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
                                    estado = true
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
                        //estado.value = "Activo"
                    } catch (_: Exception) { }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Boton("Cerrar") {
                    mostrarProducto.value = false
                }
            }
        }
    }
}