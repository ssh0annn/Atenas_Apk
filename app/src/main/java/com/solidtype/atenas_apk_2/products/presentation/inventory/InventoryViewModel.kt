package com.solidtype.atenas_apk_2.products.presentation.inventory;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
public class InventoryViewModel : ViewModel() {

    private val _busqueda = MutableLiveData<String>()
    val busqueda: LiveData<String> = _busqueda

    private val _categoria = MutableLiveData<String>()
    val categoria: LiveData<String> = _categoria

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    fun onBusquedaChange(it: String) {
        //Filtrar búsqueda
    }

    fun onCategoriaChange(it: String) {
        //Categoría
    }

    fun onNombreChange(it: String) {
        //Nombre
    }
}
