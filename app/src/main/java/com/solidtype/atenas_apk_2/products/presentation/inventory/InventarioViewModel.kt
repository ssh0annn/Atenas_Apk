package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.gestion_proveedores.domain.casos_usos.casos_proveedores.CasosProveedores
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.products.domain.userCases.CasosInventario
import com.solidtype.atenas_apk_2.products.domain.userCases.getProductos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InventarioViewModel @Inject constructor(
    private val casosInventario: CasosInventario,
    private val casosProveedores: CasosProveedores
) : ViewModel() {

    private var fileSelectionListener2: FileSelectionListener2? = null

    var uiState = MutableStateFlow(ProductosViewStates())
        private set


    init {
        mostrarProductos()
    }


    fun onEvent(event: InventariosEvent) {
        when (event) {
            is InventariosEvent.ActualizarCategoria -> {
                agregarCategoria(event.catego)
            }

            is InventariosEvent.ActualizarProductos -> {
                crearProductos(event.producto)

            }

            is InventariosEvent.AgregarCategorias -> {
                agregarCategoria(event.catego)
            }

            is InventariosEvent.AgregarProductos -> {
                crearProductos(event.productos)
            }

            is InventariosEvent.EliminarProductos -> {
                eliminarProductos(event.producto)
            }

            InventariosEvent.GetCategorias -> {
                getCategorias()
            }

            InventariosEvent.GetProductos -> {
                mostrarProductos()
            }

            is InventariosEvent.eliminarCategoria -> {
                eliminarCategoria(event.catego)
            }

            is InventariosEvent.BuscarCategoria -> {
                buscarCategorias(event.any)

            }

            is InventariosEvent.BuscarProducto -> {
                buscarProductos(event.any)
            }

            InventariosEvent.Getrpoveedores -> {
                viewModelScope.launch {

                    casosProveedores.getProveedores().collect { proveedores ->
                        uiState.update {
                            it.copy(proveedores = proveedores)
                        }

                    }
                }
            }

            is InventariosEvent.BuscarProveedores -> {
                viewModelScope.launch {

                    casosProveedores.buscarProveedores(event.any).collect { proveedores ->
                        uiState.update {
                            it.copy(proveedores = proveedores)
                        }

                    }
                }
            }
        }
    }
    private fun buscarCategorias(any:String){

        viewModelScope.launch {
            casosInventario.buscarCategorias(any).collect{ catego ->
                uiState.update { it.copy(categoria = catego) }
            }


        }
    }
    private fun eliminarCategoria(catego: categoria){
        viewModelScope.launch {
            casosInventario.eliminarCategorias(catego)
        }
    }

    private fun getCategorias() {
        viewModelScope.launch {
            casosInventario.getCategorias().collect { categoria ->
                uiState.update { it.copy(categoria = categoria) }
            }
        }
    }

    private fun agregarCategoria(catego: categoria) {
        viewModelScope.launch {
            casosInventario.agregarCategoria(catego)
        }
    }

   private fun crearProductos(
        producto: inventario
    ) {
        viewModelScope.launch {
            casosInventario.createProductos(producto)
            withContext(Dispatchers.Default) {
                // syncProductos()
            }
        }

    }

  private  fun mostrarProductos() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                //syncProductos()
            }
            casosInventario.getProductos().collect { product ->
                uiState.update {
                    it.copy(products = product)
                }
            }
        }
    }

    fun exportarExcel() {
        viewModelScope.launch {
            println("inicia viewScope en la funcion que exporta en viewmodel")
            withContext(Dispatchers.IO) {
                println("withContext la funcion que exporta en viewmodel")
                uiState.update { it.copy(isLoading = true) }
                val path = casosInventario.exportarExcel(uiState.value.products)
                println("Se guardo el archivo en: ${path}")
                uiState.update { it.copy(isLoading = false) }
                println("Salgo del withcontext la funcion que exporta en viewmodel")
                withContext(Dispatchers.Main) {
                    uiState.update {
                        it.copy(uriPath = path)
                    }
                }
            }
            mostrarProductos()
        }
    }

   private fun eliminarProductos(producto: inventario) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                uiState.update { it.copy(isLoading = true) }
                casosInventario.deleteProductos(producto)
                uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun buscarProductos(any: String) {
        viewModelScope.launch {
            val busqueda = casosInventario.searchProductos(any)
            busqueda.collect { product ->
                uiState.update {
                    it.copy(products = product)
                }
            }
        }
    }

    fun syncProductos() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                // casosInventario.syncProductos()
            }
        }
    }


    fun importarExcel(filePath: Uri) {
        // Aquí puedes realizar las operaciones necesarias con el archivo seleccionado
        fileSelectionListener2?.onFileSelected(filePath)
        println("Este esl el patchFile $filePath")
        println("Se llamo el fileSelected")
        viewModelScope.launch {
            uiState.update { it.copy(isLoading = true) }
            withContext(Dispatchers.IO) {
                if (casosInventario.importarExcelFile(filePath)) {
                    syncProductos()
                } else {
                    uiState.update {
                        it.copy(
                            isLoading = false, errorMessages = "Formato invalido"
                        )
                    }

                }
                casosInventario.importarExcelFile(filePath)
                uiState.update { it.copy(isLoading = false) }

            }

        }

    }

    interface FileSelectionListener2 {
        fun onFileSelected(filePath: Uri)
    }
}


    

