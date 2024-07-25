package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.ui.theme.AzulGris
import com.solidtype.atenas_apk_2.ui.theme.GrisOscuro
import com.solidtype.atenas_apk_2.util.ui.components.AutocompleteSelect
import com.solidtype.atenas_apk_2.util.ui.components.Carrito
import com.solidtype.atenas_apk_2.util.ui.components.InputDetalle
import com.solidtype.atenas_apk_2.util.ui.Pantalla

@Composable
@OptIn(ExperimentalMultiplatform::class)
fun Detalles(
    categoria: MutableState<String>,
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
    provider: MutableState<String>,
    mostrarCategoria: MutableState<Boolean>,
    mostrarProveedor: MutableState<Boolean>,
    uiCategoria: List<categoria>,
    uiProveedores:  List<Personastodas. Proveedor>
) {
    Column(
        modifier = Modifier
            .padding(top = 0.dp)
            .height(Pantalla.alto * 0.6f)
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
                                if (
                                    uiCategoria.isNotEmpty() &&
                                    uiCategoria
                                        .filter { it.estado }
                                        .map { it.nombre }
                                        .isNotEmpty()
                                )
                                    uiCategoria
                                        .filter { it.estado }
                                        .map { it.nombre }
                                else
                                    listOf(""),
                                true,
                                onClickAgregar = {
                                    mostrarCategoria.value = true
                                },
                            ) { valor ->
                                categoria.value = valor
                                if (categoria.value != "")
                                    idCatalogo.value =
                                        uiCategoria.find { it.nombre == categoria.value }!!.id_categoria.toString()
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            InputDetalle(
                                "Nombre", nombre.value, true
                            ) { nombre.value = it }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ) {// Codigo, Descripción, Precio y Cantidad
                        InputDetalle(
                            "Código",
                            idInventario.value,
                            tipo = KeyboardType.Number
                        ) { idInventario.value = it }
                        InputDetalle(
                            "Descripción", descripcion.value
                        ) { descripcion.value = it }
                        Spacer(modifier = Modifier.height(5.dp))
                        AutocompleteSelect(
                            "Proveedor",
                            provider.value,
                            if (uiProveedores.isNotEmpty()) uiProveedores.map { it.nombre.toString() } else listOf(
                                ""
                            ),
                            onClickAgregar = {
                                mostrarProveedor.value = true
                            },
                        ) { valor ->
                            provider.value = valor
                            if (provider.value != "")
                                idProveedor.value =
                                    uiProveedores.find { it.nombre == provider.value }!!.id_proveedor.toString()
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        InputDetalle("Costo", costo.value, tipo = KeyboardType.Number) {
                            costo.value = it
                        }
                        InputDetalle("Precio de Venta", precio.value, tipo = KeyboardType.Number) {
                            precio.value = it
                        }
                        InputDetalle("Impuesto", impuesto.value, tipo = KeyboardType.Number) {
                            impuesto.value = it
                        }
                        InputDetalle("Modelo", modelo.value) {
                            modelo.value = it
                        }
                        InputDetalle("Marca", marca.value) {
                            marca.value = it
                        }
                        InputDetalle("Cantidad", cantidad.value, tipo = KeyboardType.Number) {
                            cantidad.value = it
                        }
                    }
                }
            }
        }
    }
}