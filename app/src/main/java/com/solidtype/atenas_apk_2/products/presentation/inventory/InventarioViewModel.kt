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

    private var switch: Boolean = true
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
                    casosProveedores.proveedoresTodos().collect{ proveedores ->
                        uiState.update {
                            it.copy(proveedores = proveedores)
                        }
                    }
                }
            }

            is InventariosEvent.BuscarProveedores -> {
                viewModelScope.launch {

                    casosProveedores.buscarProveedores(event.any, switch).collect { proveedores ->
                        uiState.update {
                            it.copy(proveedores = proveedores)
                        }

                    }
                }
            }

            is InventariosEvent.CrearProveedor -> {
                viewModelScope.launch { casosProveedores.crearProveedor(event.provee) }

            }
            is InventariosEvent.EliminarProveedor -> {
                viewModelScope.launch { casosProveedores.eliminarPersona(event.provee) }
            }

            InventariosEvent.Switch -> {
                switch = !switch
                uiState.update { it.copy(switch = switch) }
                mostrarProductos()
                getCategorias()
                onEvent(InventariosEvent.Getrpoveedores)
            }

            InventariosEvent.LimpiarMensaje -> uiState.update { it.copy(messages = "") }
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
            casosInventario.getCategorias(switch).collect { categoria ->
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
            casosInventario.getProductos(switch).collect { product ->
                uiState.update {
                    it.copy(products = product)
                }
            }
        }
    }

    fun exportarExcel() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                uiState.update { it.copy(isLoading = true) }
                val path = casosInventario.exportarExcel(uiState.value.products)
                uiState.update { it.copy(isLoading = false) }
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
            val busqueda = casosInventario.searchProductos(any, switch)
            busqueda.collect { product ->
                uiState.update {
                    it.copy(products = product)
                }
            }
        }
    }

    fun syncProductos() {
    }
    fun importarExcel(filePath: Uri) {
        fileSelectionListener2?.onFileSelected(filePath)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                uiState.update { it.copy(isLoading = true, messages = "Espere...") }
                if (casosInventario.importarExcelFile(filePath)) {
                    syncProductos()
                    uiState.update {
                        it.copy(
                            isLoading = false, messages = "Se inserto con exito!!"
                        )
                    }
                } else {
                    uiState.update {
                        it.copy(
                            isLoading = false, messages = "Formato invalido"
                        )
                    }
                }
            }
        }
    }

    interface FileSelectionListener2 {
        fun onFileSelected(filePath: Uri)
    }
}


    

