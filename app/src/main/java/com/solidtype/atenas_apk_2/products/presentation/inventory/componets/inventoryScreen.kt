package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventoryViewModel

@Composable
fun InventoryScreen(/*context: Context, nav: NavController,*/ viewModel: InventoryViewModel = hiltViewModel()) {
    //val logeado:Boolean by LoginViewModel.logeado.observeAsState(initial = true)
    //val logeado = true;
    val busqueda: String by viewModel.busqueda.observeAsState(initial = "")
    val productos = listOf(
        "Manzana" to "$2.79",
        "Pera" to "$1.99",
        "Naranja" to "$1.49",
        "Plátano" to "$0.99",
        "Papaya" to "$3.99",
        "Sandía" to "$4.99",
        "Melón" to "$2.99",
        "Piña" to "$1.99",
        "Uva" to "$3.49",
        "Fresa" to "$2.99",
        "Mango" to "$1.99",
        "Kiwi" to "$0.99"
    )

    if (false) {
        //nav.navigate(Screens.Login.route)
    } else {
        Column {
            Row {//Título, Buscador, Area de Productos y Detalles
                Column(
                    modifier = Modifier.padding(start = 30.dp)
                ) {//Título, Buscador y Area de Productos
                    Row(
                        modifier = Modifier
                            .padding(20.dp, 50.dp, 0.dp, 20.dp)
                            .width(500.dp)
                    ) {//Título y Buscador
                        Text(
                            text = "Inventario",
                            modifier = Modifier.padding(top = 15.dp, end = 0.dp),
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(parseColor("#343341"))
                            )
                        ) //Título
                        Buscador(
                            busqueda = busqueda,
                            onBusquedaChange = { viewModel.onBusquedaChange(it) }
                        )
                    }
                    //Area de productos
                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .width(500.dp)
                            .height(350.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(parseColor("#343341")))
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color(parseColor("#737A8C")))
                        ) { //buscar componente para agregar filas de cards
                            item {
                                productos.chunked(4).forEach { row ->
                                    Row {
                                        row.forEach { (name, price) ->
                                            CardProduct(name, price)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier.padding(top = 46.dp)
                ) {//Detalles = Area de detalles y Botones
                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp, top = 10.dp)
                            .width(300.dp)
                            .height(430.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(parseColor("#343341"))),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp, 10.dp, 10.dp, 5.dp)
                                .fillMaxWidth()
                                .height(350.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(Color(parseColor("#737A8C")))
                        ) {// Area de detalles = Imagen del producto, Categoría y Nombre
                            Row(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                //Image(painter = /*viewModel.imagenProducto.value*/, contentDescription = "Imagen del producto")
                                Box(
                                    modifier = Modifier.padding(top = 10.dp)
                                ) {
                                    Carrito(true) //aquí debería ir la imagen del producto
                                }
                                Column {// Categoría y Nombre
                                    // hay que crear un componente si se repetirá mucho el textEdit; aquí van dos para la Categoría y Nombre
                                    InputDetalle(
                                        "Categoria",
                                        true
                                    ) { /*viewModel.onCategoriaChange(it)*/ }
                                    InputDetalle(
                                        "Nombre",
                                        true
                                    ) { /*viewModel.onNombreChange(it)*/ }
                                }
                            }
                            Column {// Codigo, Descripción, Precio y Cantidad
                                InputDetalle("Codigo") { /*viewModel.onCodigoChange(it)*/ }
                                InputDetalle("Descripción") { /*viewModel.onDescripcionChange(it)*/ }
                                InputDetalle("Precio") { /*viewModel.onPrecioChange(it)*/ }
                                InputDetalle("Cantidad") { /*viewModel.onCantidadChange(it)*/ }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {//Botones de cerrar y guardar
                            BotonIconCircular(
                                true,
                                onClick = {
                                    /*viewModel.onCerrarDetalles()*/
                                })
                            Spacer(modifier = Modifier.width(60.dp))
                            BotonIconCircular(
                                false,
                                onClick = {
                                    /*viewModel.onGuardarDetalles()*/
                                }
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.padding(50.dp, 20.dp, 0.dp, 0.dp)
            ) { //Avatar y Botones
                //Avatar
                if (true) { // si no hay imagen de perfil
                    Avatar()
                } else {
                    //Mostrar foto de perfil
                }
                Spacer(modifier = Modifier.width(330.dp))
                Row {
                    //Botones para Importar, Exportar y Ver
                    Boton("Importar", onClick = { /*viewModel.onImportar()*/ })
                    Boton("Exportar", onClick = { /*viewModel.onExportar()*/ })
                    Boton("Ver", onClick = { /*viewModel.onVer()*/ })
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 920, heightDp = 600)
@Composable
fun InventoryScreenPreview() {
    InventoryScreen()
}