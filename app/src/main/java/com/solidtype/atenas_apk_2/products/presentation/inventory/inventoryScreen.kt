package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
import com.solidtype.atenas_apk_2.util.ui.Components.Boton
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.BotonIconCircular
import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Buscador
import com.solidtype.atenas_apk_2.util.ui.Components.Carrito
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

    (context as? Activity)?.startActivityForResult(intent, 2)
}


@OptIn(ExperimentalMultiplatform::class)
@Composable
fun InventoryScreen() {
    //val logeado:Boolean by InventarioViewModel.logeado.observeAsState(initial = true)
    //val logeado = true;

    val viewModel: InventarioViewModel = hiltViewModel()

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var busqueda by rememberSaveable { mutableStateOf("") }
    var mostrar by rememberSaveable { mutableStateOf(false) }

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

    if (false) {
        //nav.navigate(Screens.Login.route)
    } else if (uiState.isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        if (uiState.uriPath.isNotBlank()) {

            SnackBar(uiState.uriPath,
                onShareClick = {
                    Toast.makeText(context, "Pulsaste compartir", Toast.LENGTH_LONG).show()
                },
                onViewClick = {
                    Toast.makeText(context, "Pulsaste View", Toast.LENGTH_LONG).show()
                })
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Row {//Título, Buscador, Area de Productos y Detalles
                    Column {//Título, Buscador y Area de Productos
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 20.dp)
                                .width(610.dp)
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
                                .width(600.dp)
                                .height(350.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(parseColor("#343341")))
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 20.dp)
                                    .width(500.dp)
                                    .verticalScroll(rememberScrollState())
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
                                    .width(600.dp)
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
                                        /*productos.chunked(4)
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
                                            }*/
                                        Row {
                                            Text(text = "imagen", modifier = Modifier.weight(1f), color = Color(0xFFFFFFFF)) // Aquí debería ir la imagen del producto
                                            Text(text = "Código", modifier = Modifier.weight(1f), color = Color(0xFFFFFFFF))
                                            Text(text = "Producto", modifier = Modifier.weight(1f), color = Color(0xFFFFFFFF))
                                            Text(text = "Precio", modifier = Modifier.weight(1f), color = Color(0xFFFFFFFF))
                                            Text(text = "Cantidad", modifier = Modifier.weight(1f), color = Color(0xFFFFFFFF))
                                        }
                                        Divider()
                                    }
                                    items(productos) {
                                        Row {
                                            Carrito(false) // Aquí debería ir la imagen del producto
                                            Text(text = it.Code_Product.toString(), modifier = Modifier.weight(1f))
                                            Text(text = it.Name_Product, modifier = Modifier.weight(1f))
                                            Text(text = it.Price_Product.toString(), modifier = Modifier.weight(1f))
                                            Text(text = it.Count_Product.toString(), modifier = Modifier.weight(1f))
                                        }
                                        Divider()
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .width(350.dp)
                    ) {//Detalles = Area de detalles y Botones
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .width(350.dp)
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
                                            InputDetalle(
                                                "Categoria", true, categoria
                                            ) { categoria = it }
                                            InputDetalle(
                                                "Nombre", true, nombre
                                            ) { nombre = it }
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 10.dp)
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
                                    }
                                )
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
                    modifier = Modifier.padding(top = 10.dp)
                ) { //Avatar y Botones
                    //Avatar
                    if (true) { // si no hay imagen de perfil
                        Avatar()
                    } else {//Mostrar foto de perfil
                        //Image(painter = , contentDescription = )
                    }

                    Spacer(modifier = Modifier.width(400.dp))

                    Row {
                        //Botones para Importar, Exportar y Ver
                        Boton("Importar") {
                            showFilePicker(context)
                        }
                        Boton("Exportar") {
                            Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
                                .show()
                            viewModel.exportarExcel()
                        }
                        Boton("Ejemplar") {
                            mostrar = true
                        }
                    }
                }
            }
        }
        Dialogo(mostrar = mostrar) {
            mostrar = false
        }
    }
}

//Preview para Vortex T10M (T10MPROPLUS) Horizontal
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, widthDp = 1080, heightDp = 560)
@Composable
fun InventoryScreenPreview() {
    InventoryScreen()
}

@Composable
fun SnackBar(
    message: String,
    onShareClick: () -> Unit,
    onViewClick: () -> Unit
) {
    Dialog(
        onDismissRequest = {

        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xAACCD2E4),
                                Color(0xAA727694),
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .blur(5.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0x00FFFFFF),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xAAFFFFFF),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = message,
                        color = Color(0xFF343341),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Boton("Cerrar") {

                    }
                }
            }
        }
    )
}




