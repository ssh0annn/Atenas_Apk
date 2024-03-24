package com.solidtype.atenas_apk_2.util

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YourViewModel @Inject constructor(): ViewModel() {
    var fileSelectionListener: FileSelectionListener? = null

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
