package com.solidtype.atenas_apk_2.products.presentation.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.userCases.CasosInventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class InventarioViewModel @Inject constructor(private val casosInventario: CasosInventario): ViewModel() {

    private val _uiState = MutableStateFlow(ProductosViewStates())
    val uiState: StateFlow<ProductosViewStates> = _uiState.asStateFlow()


            init {
                _uiState.update { it.copy(isLoading = true) }
                mostrarProductos()
                syncProductos()
                exportarExcel()

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

                    _uiState.update { it.copy(isLoading = true) }
                    productos.collect{ product ->
                       _uiState.update {
                            it.copy(products = product)
                        }
                    }
                    _uiState.update { it.copy(isLoading = false) }
                }

                }
            fun selecionarUnProducto(){
                //TODO
            }

            fun importarExcell(path:String){
                viewModelScope.launch {
                    casosInventario.importarExcelFile(path)
                }

            }

            fun exportarExcel(){
                println("Llamaron la funcion que exporta en viewmodel")
                viewModelScope.launch {
                    println("inicia viewScope en la funcion que exporta en viewmodel")

                        _uiState.update { it.copy(isLoading = true)}

                        println("withContext la funcion que exporta en viewmodel")
                    _uiState.update { it.copy(isLoading = true) }
                   val path = async { casosInventario.exportarExcel()}.await()
                    _uiState.update { it.copy(pathExcel = path) }
                    _uiState.update { it.copy(isLoading = false) }
                        println("Salgo del withcontext la funcion que exporta en viewmodel")


                    println(" fuera del withcontext, en el viewscope")
                }
                println("FIn")
            }
            fun eliminarProductos(producto:ProductEntity){
                viewModelScope.launch {
                    withContext(Dispatchers.IO){
                        _uiState.update { it.copy(isLoading = true) }
                        casosInventario.deleteProductos(producto)
                        _uiState.update { it.copy(isLoading = false) }
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


    

