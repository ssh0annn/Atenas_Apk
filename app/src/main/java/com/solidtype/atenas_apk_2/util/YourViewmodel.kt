package com.solidtype.atenas_apk_2.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.MainActivity
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.userCases.CasosInventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class YourViewModel @Inject constructor(private val casosInventario: CasosInventario): ViewModel() {
    var fileSelectionListener: FileSelectionListener? = null

    init {

        casosInventario.getProductos()
        viewModelScope.launch {
            casosInventario.createProductos(ProductEntity(
                9090,
                "Adderlis",
                "Ninguna ",
                "Boberia",
                2.3,
                "12091A",
                89.23,
                "Samsung",
                8
            )
            )
            withContext(Dispatchers.Default){
                casosInventario.syncProductos()
            }
        }
    }

    fun fileSelected(filePath: String) {
        // Aquí puedes realizar las operaciones necesarias con el archivo seleccionado
        fileSelectionListener?.onFileSelected(filePath)
        println("Este esl el patchFile $filePath")

    }

    interface FileSelectionListener {
        fun onFileSelected(filePath: String)
    }

    private fun observeViewModel() {
        fileSelectionListener = object : FileSelectionListener {
            override fun onFileSelected(filePath: String) {
                println("Ruta del archivo: $filePath")
                // Aquí puedes manejar la lógica adicional según tus necesidades
            }
        }
    }
}
