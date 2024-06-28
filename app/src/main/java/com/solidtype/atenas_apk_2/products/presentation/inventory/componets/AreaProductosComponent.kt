package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.products.presentation.inventory.ProductosViewStates
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.Blanco
import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.formatoActivo
import com.solidtype.atenas_apk_2.util.ui.Components.Carrito
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
    estado: MutableState<String>
) {
    Box(
        modifier = Modifier
            .padding(start = 20.dp)
            .width(Pantalla.ancho - 550.dp)
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
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 0.dp, end = 10.dp, bottom = 10.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp))
                        .background(GrisOscuro)
                ) { //buscar componente para agregar filas de cards
                    items(uiState.products) {
                        Row(
                            modifier = Modifier
                                .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(GrisClaro)
                                .clickable {
                                    idInventario.value =
                                        it.id_inventario.toString()
                                    idCatalogo.value =
                                        it.id_categoria.toString()
                                    idProveedor.value =
                                        it.id_proveedor.toString()
                                    nombre.value = it.nombre!!
                                    marca.value = it.marca!!
                                    modelo.value = it.modelo!!
                                    cantidad.value = it.cantidad.toString()
                                    costo.value = it.precio_compra.toString()
                                    precio.value = it.precio_venta.toString()
                                    impuesto.value = it.impuesto.toString()
                                    descripcion.value = it.descripcion!!
                                    estado.value = it.estado.formatoActivo()
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Carrito(false)
                            }// Aquí debería ir la imagen del producto
                            Text(
                                text = it.id_inventario.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = it.nombre!!,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = it.precio_venta.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = it.cantidad.toString(),
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}