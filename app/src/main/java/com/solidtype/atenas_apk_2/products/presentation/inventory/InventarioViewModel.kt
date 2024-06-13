package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.products.domain.userCases.CasosInventario
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
) : ViewModel() {

    private var fileSelectionListener2: FileSelectionListener2? = null

    var uiState = MutableStateFlow(ProductosViewStates())
        private set


    init {
        mostrarProductos()
    }

    fun crearProductos(
        id_categoria: Long,
        id_proveedor: Long,
        nombre: String,
        marca: String?,
        modelo: String?,
        cantidad: Int,
        precio_compra: Double,
        precio_venta: Double,
        impuesto : Double,
        descripcion: String?,
        estado: Boolean

    ) {

        val entidad = inventario(
            id_categoria=id_categoria,
            id_proveedor=id_proveedor,
            nombre=nombre,
            marca=marca,
            modelo=modelo,
            cantidad=cantidad,
            precio_compra=precio_compra,
            precio_venta=precio_venta,
            impuesto = impuesto,
            descripcion=descripcion,
            estado =estado
        )
        viewModelScope.launch {
            casosInventario.createProductos(entidad)
            withContext(Dispatchers.Default) {
               // syncProductos()
            }
        }

    }

    fun mostrarProductos() {
        val productos = casosInventario.getProductos()

        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                //syncProductos()
            }
            productos.collect { product ->
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
                        it.copy(uriPath = path.path.toString())
                    }
                }
            }
            mostrarProductos()
        }

    }

    fun eliminarProductos(producto: inventario) {
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


    

