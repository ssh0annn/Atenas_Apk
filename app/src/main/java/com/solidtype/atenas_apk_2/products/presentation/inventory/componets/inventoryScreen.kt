package com.solidtype.atenas_apk_2.products.presentation.inventory.componets

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.products.presentation.inventory.InventarioViewModel

fun showFilePicker(context: Context) {

    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "*/*"
        putExtra(
            Intent.EXTRA_MIME_TYPES,
            arrayOf(
                "application/vnd.ms-excel",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            )
        )
    }

    (context as? Activity)?.startActivityForResult(intent, 1)


}

@Composable
fun InventoryScreen(/*context: Context, nav: NavController,*/ viewModel: InventarioViewModel = hiltViewModel()) {
    //val logeado:Boolean by InventarioViewModel.logeado.observeAsState(initial = true)
    //val logeado = true;
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val busqueda = remember { mutableStateOf("") }

//    val productos = listOf(
//        "Manzana" to "$2.79",
//        "Pera" to "$1.99",
//        "Naranja" to "$1.49",
//        "Plátano" to "$0.99",
//        "Papaya" to "$3.99",
//        "Sandía" to "$4.99",
//        "Melón" to "$2.99",
//        "Piña" to "$1.99",
//        "Uva" to "$3.49",
//        "Fresa" to "$2.99",
//        "Mango" to "$1.99",
//        "Kiwi" to "$0.99"
//    )

    val productos = uiState.products


    if (false) {
        //nav.navigate(Screens.Login.route)
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Row {//Título, Buscador, Area de Productos y Detalles
                    Column(
                        //modifier = Modifier.padding(start = 30.dp)
                    ) {//Título, Buscador y Area de Productos
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 20.dp)
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
                                busqueda = busqueda.value,
                            ){ busqueda.value = it }
                        }
                        //Area de productos
                        Box(
                            modifier = Modifier
                                //.padding(start = 20.dp)
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
                                    productos.chunked(4)
                                        .forEach { row -> //chunked(4) = 4 productos por fila
                                            Row {//productos es una lista de objetos
                                                row.forEach { product ->
                                                    if (busqueda.value.isEmpty() || product.Name_Product?.contains(busqueda.value, ignoreCase = true) == true)
                                                    if (product.Name_Product != null && product.Price_Product != null) {
                                                        CardProduct(
                                                            product.Name_Product,
                                                            product.Price_Product.toString()
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.padding(top = 30.dp)
                    ) {//Detalles = Area de detalles y Botones
                        Column(
                            modifier = Modifier
                                .padding(start = 30.dp, top = 0.dp)
                                .width(300.dp)
                                .height(430.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(parseColor("#343341"))),
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(10.dp, 10.dp, 10.dp, 5.dp)
                                    .fillMaxWidth()
                                    .height(350.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color(parseColor("#737A8C")))
                            ) {// Area de detalles = Imagen del producto, Categoría y Nombre
                                item {
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
                                        InputDetalle("Código") { /*viewModel.onCodigoChange(it)*/ }
                                        InputDetalle("Descripción") { /*viewModel.onDescripcionChange(it)*/ }
                                        InputDetalle("Precio de Importe") { /*viewModel.onPrecioChange(it)*/ }
                                        InputDetalle("Precio de Venta") { /*viewModel.onPrecioVentaChange(it)*/ }
                                        InputDetalle("Modelo") { /*viewModel.onModeloChange(it)*/ }
                                        InputDetalle("Marca") { /*viewModel.onMarcaChange(it)*/ }
                                        InputDetalle("Cantidad") { /*viewModel.onCantidadChange(it)*/ }
                                        InputDetalle("ITBIS") { /*viewModel.onItebisChange(it)*/ }
                                        InputDetalle("Fecha de Inicio") { /*viewModel.onFechaInicioChange(it)*/ }
                                    }
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
                    modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
                ) { //Avatar y Botones
                    //Avatar
                    if (true) { // si no hay imagen de perfil
                        Avatar()
                    } else {//Mostrar foto de perfil
                        //Image(painter = , contentDescription = )
                    }
                    Spacer(modifier = Modifier.width(330.dp))
                    Row {
                        //Botones para Importar, Exportar y Ver
                        Boton("Importar", onClick = { showFilePicker(context) })
                        Boton("Exportar", onClick = { /*viewModel.onExportar()*/ })
                        Boton("Ver", onClick = { /*viewModel.onVer()*/ })
                    }
                }
            }
        }
    }
}

//Preview para Vortex T10M (T10MPROPLUS) Horizontal
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun InventoryScreenPreview() {
    InventoryScreen()
}