package com.solidtype.atenas_apk_2.util

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.products.domain.userCases.CasosInventario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class YourViewModel @Inject constructor(private val casosInventario: CasosInventario): ViewModel() {
    var fileSelectionListener: FileSelectionListener? = null
    private val switch: Boolean = true

    init {

         casosInventario.getProductos(switch)

    }

    fun fileSelected(filePath: Uri) {
        // Aquí puedes realizar las operaciones necesarias con el archivo seleccionado
        fileSelectionListener?.onFileSelected(filePath)
        println("Este esl el patchFile $filePath")
        println("Se llamo el fileSelected")
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                casosInventario.importarExcelFile(filePath) }

            }


    }

    interface FileSelectionListener {
        fun onFileSelected(filePath: Uri)
    }

    private fun observeViewModel() {
        fileSelectionListener = object : FileSelectionListener {
            override fun onFileSelected(filePath: Uri) {
                println("Ruta del archivo: $filePath")
                println("Veamos si el oserver observa !!")
                // Aquí puedes manejar la lógica adicional según tus necesidades
            }
        }
    }
}
