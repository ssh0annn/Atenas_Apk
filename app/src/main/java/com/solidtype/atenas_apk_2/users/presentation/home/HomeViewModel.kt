package com.solidtype.atenas_apk_2.users.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.users.presentation.pantallas.Screens
import androidx.navigation.NavController
import com.solidtype.atenas_apk_2.users.domain.userCase.All_useCases
import kotlinx.coroutines.launch

class HomeViewModel (private val casos_usos: All_useCases= All_useCases()): ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: MutableLiveData<String> = _name


    private val _logeado = MutableLiveData<Boolean>()
    val logeado: MutableLiveData<Boolean> = _logeado

    init {
        cambiarLoginState(true)
        viewModelScope.launch{
            if(casos_usos.current_user() != null){
                setName(casos_usos.current_user()!!.email.toString())
                 println("Este es el UID: ${casos_usos.current_user()!!.uid} <--")
            }
        }
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun setLogeado(logeado: Boolean) {
        _logeado.value = logeado
    }

    fun deslogear() {
        //Aquí la lógica de deslogeo

        casos_usos.logout()
        cambiarLoginState(false)

    }

    fun cambiarLoginState(estado : Boolean){
        _logeado.value=estado
    }
}