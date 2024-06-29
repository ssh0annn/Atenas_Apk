package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivoDDBB
import com.solidtype.atenas_apk_2.util.ui.Components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.Components.Carrito
import com.solidtype.atenas_apk_2.util.ui.Components.InputDetalle
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
@OptIn(ExperimentalMultiplatform::class)
fun Detalles(
    viewModel: InventarioViewModel,
    categoria: MutableState<String>,
    categoriaList: List<String>,
    nombre: MutableState<String>,
    idInventario: MutableState<String>,
    descripcion: MutableState<String>,
    costo: MutableState<String>,
    precio: MutableState<String>,
    modelo: MutableState<String>,
    marca: MutableState<String>,
    cantidad: MutableState<String>,
    idCatalogo: MutableState<String>,
    idProveedor: MutableState<String>,
    impuesto: MutableState<String>,
    estado: MutableState<String>,
    context: Context
) {
    Column(
        modifier = Modifier
            .padding(top = 0.dp)
            .height(Pantalla.alto - 185.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(AzulGris)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp, 10.dp, 10.dp, 5.dp)
                    .fillMaxWidth()
                    .height(Pantalla.alto - 270.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(GrisOscuro)
            ) {// Area de detalles = Imagen del producto, Categoría y Nombre
                item {
                    Row(
                        modifier = Modifier.padding(10.dp, 10.dp, 10.dp, 0.dp)
                    ) {
                        //Image(painter = /*viewModel.imagenProducto.value*/, contentDescription = "Imagen del producto")
                        Box(
                            modifier = Modifier.padding(top = 30.dp, end = 10.dp)
                        ) {
                            Carrito(true) //aquí debería ir la imagen del producto
                        }
                        Column {// Categoría y Nombre
                            // hay que crear un componente si se repetirá mucho el textEdit; aquí van dos para la Categoría y Nombre
                            AutocompleteSelect(
                                "Categoría",
                                categoria.value,
                                categoriaList,
                                true,
                                onClickAgregar = {},
                            ) {
                                categoria.value = it
                            }
                            InputDetalle(
                                "Nombre", nombre.value, true
                            ) { nombre.value = it }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp)
                    ) {// Codigo, Descripción, Precio y Cantidad
                        InputDetalle(
                            "Código",
                            idInventario.value
                        ) { idInventario.value = it }
                        InputDetalle(
                            "Descripción", descripcion.value
                        ) { descripcion.value = it }
                        InputDetalle("Costo", costo.value) {
                            costo.value = it
                        }
                        InputDetalle("Precio de Venta", precio.value) {
                            precio.value = it
                        }
                        InputDetalle("Modelo", modelo.value) {
                            modelo.value = it
                        }
                        InputDetalle("Marca", marca.value) {
                            marca.value = it
                        }
                        InputDetalle("Cantidad", cantidad.value) {
                            cantidad.value = it
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {//Botones de cerrar y guardar
                BotonIconCircular(
                    true,
                    onClick = {//Boton X para borrar productos
                        try {
                            viewModel.eliminarProductos(
                                inventario(
                                    id_inventario = idInventario.value.toLong(),
                                    id_categoria = idCatalogo.value.toLong(),
                                    id_proveedor = idProveedor.value.toLong(),
                                    nombre = nombre.value,
                                    marca = marca.value,
                                    modelo = modelo.value,
                                    cantidad = cantidad.value.toInt(),
                                    precio_compra = costo.value.toDouble(),
                                    precio_venta = costo.value.toDouble(),
                                    impuesto = impuesto.value.toDouble(),
                                    descripcion = descripcion.value,
                                    estado = estado.value.formatoActivoDDBB()
                                )

                            )
                            idInventario.value = ""
                            idCatalogo.value = ""
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
                                "No se pudo eliminar",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                )
                Spacer(modifier = Modifier.width(60.dp))
                BotonIconCircular(false, onClick = { //Guardar productos
                    try {
                        viewModel.crearProductos(
                            id_categoria = idCatalogo.value.toLong(),
                            id_proveedor = idProveedor.value.toLong(),
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
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "error: campos invalidos",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }/*viewModel.onGuardarDetalles()*/
                })
            }
        }
    }
}