package com.solidtype.atenas_apk_2.products.presentation.ejemploPresentacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.userCases.CasosInventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class NewsViewModel @Inject constructor(private val casosInventario: CasosInventario): ViewModel() {

    private val _uiState = MutableStateFlow(ProductosViewStates())
    val uiState: StateFlow<ProductosViewStates> = _uiState.asStateFlow()


            init {
                mostrarProductos()
                crearProductos(
                    "30",
                    "Mierda",
                    "klajsdfkljasdkf",
                    "ajskdfjaksdlfj",
                    "25.25",
                    "klajskdlfjasdf",
                    "25.22",
                    "lajsdfkljadsf",
                    "25",
                    )
                println("Aqui debe estar la busqueda")
                buscarProductos("M")

            }

            fun crearProductos(
                Code_Product : String?,
                Name_Product : String?,
                Description_Product : String?,
                Category_Product : String?,
                Price_Product : String?,
                Model_Product : String?,
                Price_Vending_Product : String?,
                Tracemark_Product : String?,
                Count_Product : String?
            ){
                val entidad =   ProductEntity(
                    Code_Product!!.toInt(),
                    Name_Product,
                    Description_Product,
                    Category_Product,
                    Price_Product!!.toDouble(),
                    Model_Product,
                    Price_Vending_Product!!.toDouble(),
                    Tracemark_Product,
                    Count_Product!!.toInt()

                )
                viewModelScope.launch {
                    casosInventario.createProductos(entidad)

                }
            }

        private fun mostrarProductos(){
                val productos =casosInventario.getProductos()
                viewModelScope.launch {
                    syncProductos()
                    _uiState.update { it.copy(isLoading = true) }
                    productos.collect{ product ->
                        for(i in product){
                            if(i.Name_Product != null){

                                println(i.Name_Product)
                            }else{
                                println("No hay productos palomo! ")
                            }


                        }
                        _uiState.update {
                            it.copy(products = product)
                        }
                    }
                    _uiState.update { it.copy(isLoading = false) }
                }

                }

            fun importarExcell(path:String){
                viewModelScope.launch {
                    casosInventario.importarExcelFile(path)
                }

            }

            fun exportarExcel(){
                viewModelScope.launch {
                   val path = casosInventario.exportarExcel()
                    _uiState.update { it.copy(pathExcel = path) }
                }
            }
            fun eliminarProductos(producto:ProductEntity){
                viewModelScope.launch {
                    casosInventario.deleteProductos(producto)
                }
            }
            fun ActualizarProductos(productos:ProductEntity){
                viewModelScope.launch {
                    casosInventario.updateProducto(productos)
                }

            }
            fun buscarProductos(any:String){
                viewModelScope.launch {
                    _uiState.update { it.copy(isLoading = true) }
                    val busqueda = casosInventario.searchProductos(any)
                    busqueda.collect{ product ->
                        _uiState.update {
                            it.copy(products = product)
                        }
                    }
                    _uiState.update { it.copy(isLoading = false) }
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
}


    

