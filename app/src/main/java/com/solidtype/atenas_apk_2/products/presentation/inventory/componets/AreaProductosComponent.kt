package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.products.presentation.inventory.ProductosViewStates
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.ui.components.Carrito
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
fun AreaProductos(
    uiState: ProductosViewStates,
    idInventario: MutableState<String>,
    idCatalogo: MutableState<String>,
    idProveedor: MutableState<String>,
    nombre: MutableState<String>,
    marca: MutableState<String>,
    modelo: MutableState<String>,
    cantidad: MutableState<String>,
    costo: MutableState<String>,
    precio: MutableState<String>,
    impuesto: MutableState<String>,
    descripcion: MutableState<String>,
    estado: MutableState<String>,
    mostrarProducto: MutableState<Boolean>,
    editar: MutableState<Boolean>,
    categoria: MutableState<String>,
    provider: MutableState<String>,
    mostrarConfirmarProducto: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .padding(start = 20.dp)
            .fillMaxWidth()
            .height(Pantalla.alto - 250.dp)
            .background(AzulGris, RoundedCornerShape(20.dp))
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(20.dp))
                .background(AzulGris)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(5.dp))
                    .background(GrisOscuro)
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .background(GrisOscuro)
                ) {
                    Text(
                        text = "Imagen",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    ) // Aquí debería ir la imagen del producto
                    Text(
                        text = "Código",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Producto",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Precio",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Cantidad",
                        modifier = Modifier.weight(1f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "",
                        modifier = Modifier.weight(0.5f),
                        color = Blanco,
                        textAlign = TextAlign.Center
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 0.dp, end = 10.dp, bottom = 10.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp))
                        .background(GrisOscuro)
                ) { //buscar componente para agregar filas de cards
                    items(uiState.products) { producto ->
                        Row(
                            modifier = Modifier
                                .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(GrisClaro)
                                .clickable {
                                    idInventario.value =
                                        producto.id_inventario.toString()
                                    idCatalogo.value =
                                        producto.id_categoria.toString()
                                    idProveedor.value =
                                        producto.id_proveedor.toString()
                                    nombre.value = producto.nombre
                                    marca.value = producto.marca!!
                                    modelo.value = producto.modelo!!
                                    cantidad.value = producto.cantidad.toString()
                                    costo.value = producto.precio_compra.toString()
                                    precio.value = producto.precio_venta.toString()
                                    impuesto.value = producto.impuesto.toString()
                                    descripcion.value = producto.descripcion!!
                                    estado.value = producto.estado.formatoActivo()
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Carrito(false)
                            }// Aquí debería ir la imagen del producto
                            Text(
                                text = producto.id_inventario.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = producto.nombre,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = producto.precio_venta.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = producto.cantidad.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            //Iconos de editar y eliminar
                            Row (
                                modifier = Modifier.weight(0.5f),
                                horizontalArrangement = Arrangement.End
                            ){
                                IconButton(onClick = {
                                    mostrarProducto.value = true
                                    editar.value = true

                                    idInventario.value = producto.id_inventario.toString()
                                    nombre.value = producto.nombre
                                    marca.value = producto.marca!!
                                    modelo.value = producto.modelo!!
                                    cantidad.value = producto.cantidad.toString()
                                    costo.value = producto.precio_compra.toString()
                                    precio.value = producto.precio_venta.toString()
                                    impuesto.value = producto.impuesto.toString()
                                    descripcion.value = producto.descripcion!!
                                    estado.value = producto.estado.formatoActivo()
                                    //filtrar el nombre proveedor y la categoria con el id del producto de uiState.proveedores y uiState.categoria
                                    provider.value = uiState.proveedores.find { it.id_proveedor == producto.id_proveedor }!!.nombre.toString()
                                    categoria.value = uiState.categoria.find { it.id_categoria == producto.id_categoria }!!.nombre
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = null,
                                        tint = AzulGris
                                    )
                                }
                                IconButton(onClick = {
                                    mostrarConfirmarProducto.value = true

                                    idInventario.value = producto.id_inventario.toString()
                                    nombre.value = producto.nombre
                                    marca.value = producto.marca!!
                                    modelo.value = producto.modelo!!
                                    cantidad.value = producto.cantidad.toString()
                                    costo.value = producto.precio_compra.toString()
                                    precio.value = producto.precio_venta.toString()
                                    impuesto.value = producto.impuesto.toString()
                                    descripcion.value = producto.descripcion!!
                                    estado.value = producto.estado.formatoActivo()
                                    provider.value = uiState.proveedores.find { it.id_proveedor == producto.id_proveedor }!!.nombre.toString()
                                    categoria.value = uiState.categoria.find { it.id_categoria == producto.id_categoria }!!.nombre
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = null,
                                        tint = AzulGris
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}