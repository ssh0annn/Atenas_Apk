package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.userCases.CasosInventario
import com.solidtype.atenas_apk_2.util.YourViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
@HiltViewModel
class InventarioViewModel @Inject constructor(
    private val casosInventario: CasosInventario,
    private val context: Context): ViewModel() {

    var fileSelectionListener2: FileSelectionListener2? = null

     var uiState = MutableStateFlow(ProductosViewStates())
         private set

    var excel by mutableStateOf("")
        private set


            init {

                mostrarProductos()


                }


            fun crearProductos(
                Code_Product : String,
                Name_Product : String,
                Description_Product : String,
                Category_Product : String,
                Price_Product : String,
                Model_Product : String,
                Price_Vending_Product : String,
                Tracemark_Product : String,
                Count_Product : String
            ){
                val entidad =   ProductEntity(
                    Code_Product.toInt(),
                    Name_Product,
                    Description_Product,
                    Category_Product,
                    Price_Product.toDouble(),
                    Model_Product,
                    Price_Vending_Product.toDouble(),
                    Tracemark_Product,
                    Count_Product.toInt()

                )
                viewModelScope.launch {
                    casosInventario.createProductos(entidad)
                }
                syncProductos()
            }

         fun mostrarProductos(){
                val productos =casosInventario.getProductos()
                 syncProductos()
                viewModelScope.launch {

                   // uiState.update { it.copy(isLoading = true) }
                    productos.collect{ product ->
                       uiState.update {
                            it.copy(products = product)
                        }
                       // uiState.update { it.copy(isLoading = false) }
                    }

                }

                }



            fun exportarExcel(){
                println("Llamaron la funcion que exporta en viewmodel")
                viewModelScope.launch {
                    println("inicia viewScope en la funcion que exporta en viewmodel")
                    withContext(Dispatchers.IO) {
                        println("withContext la funcion que exporta en viewmodel")
                        uiState.update { it.copy(isLoading = true) }
                        val path = casosInventario.exportarExcel(uiState.value.products)

                        println("Se guardo el archivo en: ${path}")

                         uiState.update { it.copy(isLoading = false) }
                        println("Salgo del withcontext la funcion que exporta en viewmodel")
                        withContext(Dispatchers.Main){

                        Toast.makeText(context, "Se ha creado un nuevo archivo en: ${path.path}", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                    println(" fuera del withcontext, en el viewscope")

                println("FIn")
            }
            fun eliminarProductos(producto:ProductEntity){
                viewModelScope.launch {
                    withContext(Dispatchers.IO){
                        uiState.update { it.copy(isLoading = true) }
                        casosInventario.deleteProductos(producto)
                        uiState.update { it.copy(isLoading = false) }
                    }

                }
            }
            fun ActualizarProductos(productos:ProductEntity){
                viewModelScope.launch {
                    casosInventario.updateProducto(productos)
                }

            }
            fun buscarProductos(any:String){
                viewModelScope.launch {
                    uiState.update { it.copy(isLoading = true) }
                    val busqueda = casosInventario.searchProductos(any)
                    busqueda.collect{ product ->
                        uiState.update {
                            it.copy(products = product)
                        }
                    }
                    uiState.update { it.copy(isLoading = false) }
                }

            }
            fun syncProductos(){
                viewModelScope.launch {
                    withContext(Dispatchers.Default){
                        casosInventario.syncProductos()

                    }
                }
            }

            fun mostrarEjemplarExcell(){
                //TODO hay que agregar una imagen

            }

    fun importarExcel(filePath: Uri) {
        // Aquí puedes realizar las operaciones necesarias con el archivo seleccionado
        fileSelectionListener2?.onFileSelected(filePath)
        println("Este esl el patchFile $filePath")
        println("Se llamo el fileSelected")
        viewModelScope.launch {
            uiState.update { it.copy(isLoading = true) }
            withContext(Dispatchers.IO){

                casosInventario.importarExcelFile(filePath)
                uiState.update { it.copy(isLoading = false) }
            }

        }


    }

    interface FileSelectionListener2 {
        fun onFileSelected(filePath: Uri)
    }
}


    

