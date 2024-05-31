//package com.solidtype.atenas_apk_2.products.presentation.inventory
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.shrinkVertically
//import androidx.compose.animation.slideInVertically
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Inventory2
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.outlined.Delete
//import androidx.compose.material.icons.outlined.Edit
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.DismissDirection
//import androidx.compose.material3.DismissState
//import androidx.compose.material3.DismissValue
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Snackbar
//import androidx.compose.material3.SwipeToDismiss
//import androidx.compose.material3.Text
//import androidx.compose.material3.rememberDismissState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.navigation.NavController
//import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
//import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.Buscador2
//import com.solidtype.atenas_apk_2.products.presentation.inventory.componets.DialogoV2
//import com.solidtype.atenas_apk_2.ui.theme.AzulGris
//import com.solidtype.atenas_apk_2.ui.theme.Blanco
//import com.solidtype.atenas_apk_2.ui.theme.GrisClaro
//import com.solidtype.atenas_apk_2.util.ui.Components.Avatar
//import com.solidtype.atenas_apk_2.util.ui.Components.Boton
//import com.solidtype.atenas_apk_2.util.ui.Components.BotonBlanco
//import com.solidtype.atenas_apk_2.util.ui.Components.Carrito
//import com.solidtype.atenas_apk_2.util.ui.Pantalla
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("CoroutineCreationDuringComposition")
//@Composable
//fun InventoryScreenV2(
//    navController: NavController,
//    viewModel: InventarioViewModel = hiltViewModel()
//) {
//
//    val context = LocalContext.current
//
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//    var busqueda by rememberSaveable { mutableStateOf("") }
//    var mostrar by rememberSaveable { mutableStateOf(false) }
//
//    if (busqueda.isNotBlank()) {
//        viewModel.buscarProductos(busqueda)
//    } else {
//        viewModel.mostrarProductos()
//    }
//
//    if (uiState.pathExcel!!.isNotBlank()) {
//        Toast.makeText(context, "Exportado: ${uiState.pathExcel}", Toast.LENGTH_LONG).show()
//    }
//
//    var codigo by rememberSaveable {
//        mutableStateOf("")
//    }
//    var categoria by rememberSaveable {
//        mutableStateOf("")
//    }
//    var nombre by rememberSaveable {
//        mutableStateOf("")
//    }
//    var descripcion by rememberSaveable {
//        mutableStateOf("")
//    }
//    var costo by rememberSaveable {
//        mutableStateOf("")
//    }
//    var precio by rememberSaveable {
//        mutableStateOf("")
//    }
//    var modelo by rememberSaveable {
//        mutableStateOf("")
//    }
//    var marca by rememberSaveable {
//        mutableStateOf("")
//    }
//    var cantidad by rememberSaveable {
//        mutableStateOf("")
//    }
//
//    var showSnackbar by rememberSaveable { mutableStateOf(false) }
//
//    val coroutineScope = rememberCoroutineScope()
//    var snackbarJob: Job by remember { mutableStateOf(Job()) }
//
//    var showSnackbarIni by rememberSaveable { mutableStateOf(false) }
//
//    val productos = uiState.products
//
//    val categoriaList = listOf(
//        "Accesorios",
//        "Celulares",
//        "Laptops",
//        "Tablets",
//        "Otros"
//    )//Esto debería venir del ViewModel
//
//    val uri = uiState.uriPath
//
//    var showConfirmSnackbar by rememberSaveable { mutableStateOf(false) }
//    var lastDeletedItem by remember { mutableStateOf<ProductEntity?>(null) }
//    var lastDeletedItemPosition by remember { mutableStateOf<Int?>(null) }
//
//    val isRemovedList by rememberSaveable(productos) {
//        if(productos.isEmpty())
//            mutableStateOf(emptyList())
//        else
//            mutableStateOf(List(productos.size) { mutableStateOf(false) })
//    }
//
//    val isEditedList by rememberSaveable(productos) {
//        if(productos.isEmpty())
//            mutableStateOf(emptyList())
//        else
//            mutableStateOf(List(productos.size) { mutableStateOf(false) })
//    }
//
//    val stateList =
//        if (productos.isEmpty())
//            emptyList()
//        else
//        List(productos.size) {
//        mutableStateOf(rememberDismissState(
//            confirmValueChange = { value ->
//                if (value == DismissValue.DismissedToStart) {
//                    isRemovedList.toMutableList()[productos.indexOf(productos[it])].value = true
//                    true
//                } else {
//                    false
//                }
//            }
//        ))
//    }
//
//    var deshechado by rememberSaveable {
//        mutableStateOf(false)
//    }
//
//    if (productos.isNotEmpty()) {
//        LaunchedEffect(key1 = isRemovedList) {
//            if (lastDeletedItemPosition != null && deshechado) { //Si se deshizo la eliminación
//                isRemovedList.toMutableList()[lastDeletedItemPosition!!].value = false
//                stateList[lastDeletedItemPosition!!].value.reset()
//                lastDeletedItemPosition = null
//                lastDeletedItem = null
//                deshechado = false
//            }
//        }
//    }
//
//    //Aquí empieza de verdad
//    if (false) {
//        //nav.navigate(Screens.Login.route)
//    } else if (uiState.isLoading) {
//        Box(
//            Modifier.fillMaxSize()
//        ) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//    } else {
//        if (showSnackbarIni) {
//            showSnackbarIni = false
//            snackbarJob.cancel() //Cancela el job anterior si existe
//            showSnackbar = true
//            snackbarJob = coroutineScope.launch {
//                delay(10000L)
//                showSnackbar = false
//            }
//        }
//        Column(
//            //To.do
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                .background(
//                    color = GrisClaro
//                ),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 100.dp, vertical = 20.dp)
//            ) {//Contenedor
//                //Titulo
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.Inventory2,
//                        contentDescription = "Inventario",
//                        tint = AzulGris,
//                        modifier = Modifier
//                            .size(40.dp)
//                    )
//                    Spacer(modifier = Modifier.width(10.dp))
//                    Text(
//                        text = "Inventario",
//                        color = AzulGris,
//                        fontSize = 28.sp,
//                        fontWeight = FontWeight.Bold
//                    )
//                }
//                Spacer(modifier = Modifier.height(15.dp))
//                //Buscador
//                Buscador2(
//                    busqueda = busqueda,
//                ) { busqueda = it }
//                //Tabla
//                Box(
//                    modifier = Modifier
//                        .padding(top = 10.dp)
//                        .fillMaxWidth()
//                        .background(AzulGris, RoundedCornerShape(20.dp))
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .padding(10.dp)
//                            .fillMaxWidth()
//                            .height(Pantalla.alto * 0.54f) // 54% de la pantalla
//                            .clip(RoundedCornerShape(20.dp))
//                            .background(Color(android.graphics.Color.parseColor("#343341")))
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .clip(RoundedCornerShape(5.dp))
//                                .background(Color(android.graphics.Color.parseColor("#737A8C")))
//                        ) {
//                            Row(
//                                modifier = Modifier
//                                    .padding(10.dp)
//                                    .fillMaxWidth()
//                                    .clip(RoundedCornerShape(5.dp))
//                                    .background(Color(android.graphics.Color.parseColor("#737A8C")))
//
//                            ) {
//                                Text(
//                                    text = "Imagen",
//                                    modifier = Modifier.weight(1f),
//                                    color = Color(0xFFFFFFFF),
//                                    textAlign = TextAlign.Center
//                                ) // Aquí debería ir la imagen del producto
//                                Text(
//                                    text = "Código",
//                                    modifier = Modifier.weight(1f),
//                                    color = Color(0xFFFFFFFF),
//                                    textAlign = TextAlign.Center
//                                )
//                                Text(
//                                    text = "Producto",
//                                    modifier = Modifier.weight(1f),
//                                    color = Color(0xFFFFFFFF),
//                                    textAlign = TextAlign.Center
//                                )
//                                Text(
//                                    text = "Precio",
//                                    modifier = Modifier.weight(1f),
//                                    color = Color(0xFFFFFFFF),
//                                    textAlign = TextAlign.Center
//                                )
//                                Text(
//                                    text = "Cantidad",
//                                    modifier = Modifier.weight(1f),
//                                    color = Color(0xFFFFFFFF),
//                                    textAlign = TextAlign.Center
//                                )
//                                Text(
//                                    text = "",
//                                    modifier = Modifier.weight(0.4f),
//                                    color = Color(0xFFFFFFFF),
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//                            LazyColumn(
//                                modifier = Modifier
//                                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
//                                    .fillMaxSize()
//                                    .clip(RoundedCornerShape(5.dp))
//                                    .background(Color(android.graphics.Color.parseColor("#737A8C")))
//                            ) {
//                                if(productos.isNotEmpty()){
//                                    itemsIndexed(productos) {i, it ->
//                                        SwipeContainer(
//                                            item = it,
//                                            stateItem = stateList[i],
//                                            isRemovedItem = isRemovedList[i],
//                                            isEditedItem = isEditedList[i],
//                                            onDelete = {producto ->
//                                                lastDeletedItem = producto
//                                                lastDeletedItemPosition = i
//                                                isRemovedList.toMutableList()[i].value = true
//                                                showConfirmSnackbar = true
//                                                viewModel.eliminarProductos(producto)
//                                            },
//                                            onEdit = {
//                                                Log.i("Wow", "Editando producto")
//                                            }
//                                        ) {
//                                            Row(
//                                                modifier = Modifier
//                                                    .padding(
//                                                        vertical = 5.dp,
//                                                        horizontal = 10.dp
//                                                    )
//                                                    .clip(RoundedCornerShape(10.dp))
//                                                    .background(Color(0xFFD9D9D9))
//                                                    .clickable {
//                                                        codigo = it.Code_Product.toString()
//                                                        nombre = it.Name_Product
//                                                        categoria = it.Category_Product
//                                                        descripcion = it.Description_Product
//                                                        costo = it.Price_Product.toString()
//                                                        precio = it.Price_Vending_Product.toString()
//                                                        modelo = it.Model_Product
//                                                        marca = it.Tracemark_Product
//                                                        cantidad = it.Count_Product.toString()
//                                                    },
//                                                verticalAlignment = Alignment.CenterVertically,
//                                                horizontalArrangement = Arrangement.SpaceBetween
//                                            ) {
//                                                Box(
//                                                    modifier = Modifier
//                                                        .weight(1f)
//                                                        .padding(10.dp),
//                                                    contentAlignment = Alignment.Center
//                                                ) {
//                                                    Carrito(chiquito = true)
//                                                }// Aquí debería ir la imagen del producto
//                                                Text(
//                                                    text = it.Code_Product.toString(),
//                                                    modifier = Modifier.weight(1f),
//                                                    textAlign = TextAlign.Center
//                                                )
//                                                Text(
//                                                    text = it.Name_Product,
//                                                    modifier = Modifier.weight(1f),
//                                                    textAlign = TextAlign.Center
//                                                )
//                                                Text(
//                                                    text = it.Price_Product.toString(),
//                                                    modifier = Modifier.weight(1f),
//                                                    textAlign = TextAlign.Center
//                                                )
//                                                Text(
//                                                    text = it.Count_Product.toString(),
//                                                    modifier = Modifier.weight(1f),
//                                                    textAlign = TextAlign.Center
//                                                )
//                                                Icon(
//                                                    imageVector = Icons.Default.Menu,
//                                                    contentDescription = null,
//                                                    tint = AzulGris,
//                                                    modifier = Modifier
//                                                        .padding(10.dp)
//                                                        .size(30.dp)
//                                                        .weight(0.4f)
//                                                )
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                //Botones
//                Row(
//                    modifier = Modifier.padding(top = 10.dp)
//                ) { //Avatar y Botones
//                    //Avatar
//                    if (true) { // si no hay imagen de perfil
//                        Avatar()
//                    } else {//Mostrar foto de perfil
//                        //Image(painter = , contentDescription = )
//                    }
//                    Spacer(
//                        modifier = Modifier.width(
//                            Pantalla.ancho * 0.14f
//                        )
//                    )
//                    Row {
//                        //Botones para Importar, Exportar y Ejemplar
//                        Boton("Importar") {
//                            showFilePicker2(context)
//                        }
//                        Boton("Exportar") {
//                            Toast.makeText(context, "Espere un momento...", Toast.LENGTH_SHORT)
//                                .show()
//                            viewModel.exportarExcel()
//                            showSnackbarIni = true
//                        }
//                        Boton("Ejemplar") {
//                            mostrar = true
//                        }
//                        Spacer(
//                            modifier = Modifier.width(
//                                Pantalla.ancho * 0.14f
//                            )
//                        )
//                        Boton(icon = { //Botón para agregar producto con un modal o dialog
//                            Icon(
//                                imageVector = Icons.Filled.Add,
//                                contentDescription = "Add",
//                                tint = Color.White
//                            )
//                        }) { //Modal o Dialog para agregar producto
//                            //showDialogEdit = true
//
//                        }
//                    }
//                }
//            }
//        }
//        DialogoV2(mostrar) {
//            mostrar = false
//        }
//        AnimatedVisibility(
//            visible = showSnackbar,
//            enter = slideInVertically(
//                initialOffsetY = { it },
//                animationSpec = tween(500)
//            )
//        ) {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.BottomCenter
//            ) {
//                Snackbar(
//                    action = {
//                        if (uri.isNotBlank()) {
//                            Row {
//                                BotonBlanco("Compartir") {
//                                    val fileUri = Uri.parse(uri)
//                                    val shareIntent: Intent = Intent().apply {
//                                        action = Intent.ACTION_SEND
//                                        putExtra(Intent.EXTRA_STREAM, fileUri)
//                                        type =
//                                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
//                                    }
//                                    val chooser =
//                                        Intent.createChooser(shareIntent, "Compartir archivo")
//                                    context.startActivity(chooser)
//                                }
//                                Spacer(modifier = Modifier.width(10.dp))
//                                BotonBlanco("Ver") {
//                                    val fileUri = Uri.parse(uri)
//                                    val openIntent: Intent = Intent().apply {
//                                        action = Intent.ACTION_VIEW
//                                        setDataAndType(
//                                            fileUri,
//                                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
//                                        )
//                                        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//                                    }
//                                    val chooser = Intent.createChooser(openIntent, "Abrir archivo")
//                                    if (openIntent.resolveActivity(context.packageManager) != null) {
//                                        context.startActivity(chooser)
//                                    } else {
//                                        Toast.makeText(
//                                            context,
//                                            "No se encontró una aplicación para abrir este archivo. Favor de instalar una aplicación compatible con archivos de Excel.",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//                                    }
//                                }
//                            }
//                        }
//                    },
//                    modifier = Modifier.padding(8.dp),
//                    containerColor = Color(0xFF343341)
//                ) {
//                    Text(
//                        text = if (uri.isNotBlank()) "El archivo se guardó en: $uri" else "Hubo un error al exportar",
//                        color =
//                        if (uri.isNotBlank())
//                            Color(0xFF77FF77)
//                        else
//                            Color(0xFFFF7777),
//                    )
//                }
//            }
//        }
//
//        AnimatedVisibility(
//            visible = showConfirmSnackbar,
//            enter = slideInVertically(
//                initialOffsetY = { it },
//                animationSpec = tween(500)
//            )
//        ) {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.BottomCenter
//            ) {
//                Snackbar(
//                    action = {
//                        BotonBlanco("Deshacer") {
//                            viewModel.crearProductos(
//                                lastDeletedItem!!.Code_Product.toString(),
//                                lastDeletedItem!!.Name_Product,
//                                lastDeletedItem!!.Description_Product,
//                                lastDeletedItem!!.Category_Product,
//                                lastDeletedItem!!.Price_Product.toString(),
//                                lastDeletedItem!!.Model_Product,
//                                lastDeletedItem!!.Price_Vending_Product.toString(),
//                                lastDeletedItem!!.Tracemark_Product,
//                                lastDeletedItem!!.Count_Product.toString()
//                            )
//                            deshechado = true
//                            showConfirmSnackbar = false
//                        }
//                    },
//                    modifier = Modifier.padding(8.dp),
//                    containerColor = Color(0xFF343341)
//                ) {
//                    Text(
//                        text = "El producto se eliminó correctamente",
//                        color = Blanco
//                    )
//                }
//            }
//        }
//    }
//}
//
//fun showFilePicker2(context: Context) {
//
//    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
//        addCategory(Intent.CATEGORY_OPENABLE)
//        type = "*/*"
//        putExtra(
//            Intent.EXTRA_MIME_TYPES, arrayOf(
//                "application/vnd.ms-excel",
//                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
//            )
//        )
//    }
//
//    (context as? Activity)?.startActivityForResult(intent, 2)
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun <T> SwipeContainer(
//    item: T,
//    stateItem: MutableState<DismissState>,
//    isRemovedItem: MutableState<Boolean>,
//    isEditedItem: MutableState<Boolean>,
//    onDelete: (T) -> Unit,
//    onEdit: (T) -> Unit,
//    animationDuration: Int = 500,
//    content: @Composable (T) -> Unit
//) {
//
//    stateItem.value = rememberDismissState(
//        confirmValueChange = { value ->
//            /*if (value == DismissValue.DismissedToStart) {
//                isRemovedItem.value = true
//                true
//            } else {
//                if (value != DismissValue.DismissedToEnd) {
//                    isRemovedItem.value = false
//                    false
//                }
//            }*/
//            when (value) {
//                DismissValue.DismissedToStart -> {
//                    isRemovedItem.value = true
//                    true
//                }
//                DismissValue.DismissedToEnd -> {
//                    isEditedItem.value = true
//                    true
//                }
//
//                else -> {
//                    false
//                }
//            }
//        }
//    )
//
//    LaunchedEffect(key1 = stateItem.value.dismissDirection) {
//        if(isRemovedItem.value) {
//            delay(animationDuration.toLong())
//            onDelete(item)
//            Log.i("Wow", "Eliminando producto desde el primer if")
//        }
//        if (isEditedItem.value) {
//            delay(animationDuration.toLong())
//            onEdit(item)
//        }
//    }
//
//    LaunchedEffect(key1 = stateItem.value.dismissDirection) {//Si se deshace la eliminación
//        if (stateItem.value.dismissDirection == DismissDirection.EndToStart) { // Luego de deshacer, se mantiene en false?
//            isRemovedItem.value = true
//        }
//    }
//
//    //Si swipeDismissState.dismissDirection == DismissDirection.EndToStart y la animación terminó, entonces se elimina el item
//    LaunchedEffect(key1 = stateItem.value.dismissDirection) {
//        if (stateItem.value.dismissDirection == DismissDirection.EndToStart && stateItem.value.progress == 2f) {
//            delay(animationDuration.toLong())
//            onDelete(item)
//            Log.i("Wow", "Eliminando producto desde el LaunchedEffect")
//        }
//    }
//
//    /*LaunchedEffect(key1 = stateItem.value.progress) {
//        if (stateItem.value.progress < 0.25f) {
//            stateItem.value.reset()
//        }
//    }*/
//
//    //Evitar que se deslice hacia la derecha para editar (restaurar)
//    if (isEditedItem.value) {
//        LaunchedEffect(key1 = Unit) {
//            stateItem.value.reset()
//        }
//    }
//
//    AnimatedVisibility(
//        visible = !isRemovedItem.value || !isEditedItem.value,
//        exit = shrinkVertically(
//            animationSpec = tween(durationMillis = animationDuration),
//            shrinkTowards = Alignment.Top
//        ) + fadeOut()
//    ) {
//        SwipeToDismiss(
//            state = stateItem.value,
//            background = {
//                MyBackground(swipeDismissState = stateItem.value)
//            },
//            dismissContent = { content(item) },
//            directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd)
//        )
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyBackground(
//    swipeDismissState: DismissState
//) {
//    val color =
//        /*if (swipeDismissState.dismissDirection == DismissDirection.EndToStart)
//            Color(0xFFFF7777)
//        else
//            Color.Transparent*/
//        when (swipeDismissState.dismissDirection) {
//            DismissDirection.EndToStart -> Color(0xFFFF7777)
//            DismissDirection.StartToEnd -> Color(0xFF7777FF)
//            else -> Color.Transparent
//        }
//
//    /*if (!swipeDismissState.isDismissed(DismissDirection.EndToStart)) { // Si no se deslizó hacia la izquierda
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color)
//                .padding(8.dp),
//            contentAlignment = Alignment.CenterEnd
//        ) {
//            Icon(
//                modifier = Modifier
//                    .padding(end = ((Pantalla.ancho * 0.5f) - 160.dp) * swipeDismissState.progress),
//                imageVector = Icons.Outlined.Delete,
//                contentDescription = null,
//                tint = Blanco
//            )
//        }
//    }else{
//        Box{}
//    }*/
//
//    when (swipeDismissState.dismissDirection) {
//        DismissDirection.EndToStart -> {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(color)
//                    .padding(8.dp),
//                contentAlignment = Alignment.CenterEnd
//            ) {
//                Icon(
//                    modifier = Modifier
//                        .padding(end = ((Pantalla.ancho * 0.5f) - 160.dp) * swipeDismissState.progress),
//                    imageVector = Icons.Outlined.Delete,
//                    contentDescription = null,
//                    tint = Blanco
//                )
//            }
//        }
//
//        DismissDirection.StartToEnd -> {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(color)
//                    .padding(8.dp),
//                contentAlignment = Alignment.CenterStart
//            ) {
//                Icon(
//                    modifier = Modifier
//                        .padding(start = ((Pantalla.ancho * 0.5f) - 160.dp) * swipeDismissState.progress),
//                    imageVector = Icons.Outlined.Edit,
//                    contentDescription = null,
//                    tint = Blanco
//                )
//            }
//        }
//
//        else -> {
//            Box{}
//        }
//    }
//}