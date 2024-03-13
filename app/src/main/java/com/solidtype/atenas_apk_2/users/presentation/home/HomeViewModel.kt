package com.solidtype.atenas_apk_2.users.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.solidtype.atenas_apk_2.users.presentation.pantallas.Screens
import androidx.navigation.NavController

class HomeViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: MutableLiveData<String> = _name

    private val _logeado = MutableLiveData<Boolean>()
    val logeado: MutableLiveData<Boolean> = _logeado

    fun setName(name: String) {
        _name.value = name
    }

    fun setLogeado(logeado: Boolean) {
        _logeado.value = logeado
    }

    fun deslogear() {
        //Aquí la lógica de deslogeo
        _logeado.value = false
    }
}