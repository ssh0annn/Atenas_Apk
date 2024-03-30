package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Avatar
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Boton
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.BotonIconCircular
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Buscador
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.CardProduct
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Carrito
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Dialogo
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.InputDetalle

fun showFilePicker(context: Context) {

    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "*/*"
        putExtra(
            Intent.EXTRA_MIME_TYPES, arrayOf(
                "application/vnd.ms-excel",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            )
        )
    }

    (context as? Activity)?.startActivityForResult(intent, 1)


}

@OptIn(ExperimentalMultiplatform::class)
@Composable
fun InventoryScreen(/*context: Context, nav: NavController,*/ viewModel: InventarioViewModel = hiltViewModel()) {
    //val logeado:Boolean by InventarioViewModel.logeado.observeAsState(initial = true)
    //val logeado = true;
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var busqueda by remember { mutableStateOf("") }
    var mostrar: Boolean = remember { mutableStateOf(false) }.value

    if (busqueda.isNotBlank()) {
        viewModel.buscarProductos(busqueda)
    } else {
        viewModel.mostrarProductos()
    }

    if (uiState.pathExcel!!.isNotBlank()) {
        Toast.makeText(context, "Exportado: ${uiState.pathExcel}", Toast.LENGTH_LONG).show()
    }

    var codigo by rememberSaveable {
        mutableStateOf("")
    }
    var categoria by rememberSaveable {

        mutableStateOf("")
    }
    var nombre by rememberSaveable {
        mutableStateOf("")
    }
    var descripcion by rememberSaveable {
        mutableStateOf("")
    }
    var costo by rememberSaveable {
        mutableStateOf("")
    }
    var precio by rememberSaveable {
        mutableStateOf("")
    }
    var modelo by rememberSaveable {
        mutableStateOf("")
    }
    var marca by rememberSaveable {
        mutableStateOf("")
    }
    var cantidad by rememberSaveable {
        mutableStateOf("")
    }

    val productos = uiState.products

    if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {


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
                                    busqueda = busqueda,
                                ) { busqueda = it }
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
                                                        CardProduct(
                                                            product
                                                        ) { clicked ->
                                                            codigo = "${clicked.Code_Product}"
                                                            nombre = clicked.Name_Product
                                                            categoria = clicked.Category_Product
                                                            descripcion =
                                                                clicked.Description_Product
                                                            costo = clicked.Price_Product.toString()
                                                            precio =
                                                                clicked.Price_Vending_Product.toString()
                                                            modelo = clicked.Model_Product
                                                            marca = clicked.Tracemark_Product
                                                            cantidad =
                                                                clicked.Count_Product.toString()
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
                                                    "Categoria", true, categoria
                                                ) { categoria = it }
                                                InputDetalle(
                                                    "Nombre", true, nombre
                                                ) { nombre = it }
                                            }
                                        }
                                        Column(
                                            modifier = Modifier.padding(
                                                10.dp, vertical = 5.dp
                                            )
                                        ) {// Codigo, Descripción, Precio y Cantidad
                                            InputDetalle("Código", false, codigo) { codigo = it }
                                            InputDetalle(
                                                "Descripción", false, descripcion
                                            ) { descripcion = it }
                                            InputDetalle("Costo", false, costo) { costo = it }
                                            InputDetalle("Precio de Venta", false, precio) {
                                                precio = it
                                            }
                                            InputDetalle("Modelo", false, modelo) { modelo = it }
                                            InputDetalle("Marca", false, marca) { marca = it }
                                            InputDetalle("Cantidad", false, cantidad) {
                                                cantidad = it
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
                                                    ProductEntity(
                                                        codigo.toInt(),
                                                        nombre,
                                                        descripcion,
                                                        categoria,
                                                        costo.toDouble(),
                                                        modelo,
                                                        precio.toDouble(),
                                                        marca,
                                                        cantidad.toInt()

                                                    )
                                                )
                                                codigo = ""
                                                categoria = ""
                                                nombre = ""
                                                descripcion = ""
                                                costo = ""
                                                precio = ""
                                                modelo = ""
                                                marca = ""
                                                cantidad = ""

                                            } catch (e: Exception) {
                                                Toast.makeText(
                                                    context,
                                                    "No se pudo eliminar",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }

                                        })
                                    Spacer(modifier = Modifier.width(60.dp))
                                    BotonIconCircular(false, onClick = {
                                        try {

                                            viewModel.crearProductos(

                                                Code_Product = codigo,
                                                Name_Product = nombre,
                                                Description_Product = descripcion,
                                                Category_Product = categoria,
                                                Price_Product = costo,
                                                Model_Product = modelo,
                                                Price_Vending_Product = precio,
                                                Tracemark_Product = marca,
                                                Count_Product = cantidad

                                            )
                                        } catch (e: Exception) {
                                            Toast.makeText(context, "error: $e", Toast.LENGTH_LONG)
                                                .show()
                                        }/*viewModel.onGuardarDetalles()*/
                                    })
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
                            Boton("Importar") {
                                showFilePicker(context)
                            }
                            Boton("Exportar") {
                                Toast.makeText(context, "Que bobo", Toast.LENGTH_SHORT).show()
                                viewModel.exportarExcel()

                            }
                            Boton("Ver") {
                                mostrar = true //En realidad va un viewModel.verEjemplarExcel() tipo booleano (pienso)
                            }
                        }
                    }
                    Dialogo(mostrar = mostrar) {
                        mostrar = false
                    }
                }
            }
        }
    }
}

@Composable
fun InventoryScreenPreview() {
    InventoryScreen()
}

//@Composable
//fun DefaultPreview() {
//    var mostrar: Boolean = remember { mutableStateOf(false) }.value
//    Column {
//        Text(text = "Hello, World!")
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(text = "This is a dialog")
//        Spacer(modifier = Modifier.height(16.dp))
//        Boton("Ver") {
//            mostrar = true
//        }
//        Dialogo(
//            mostrar = mostrar,
//            onCerrarDialogo = {
//                mostrar = false
//            }
//        )
//    }
//}
