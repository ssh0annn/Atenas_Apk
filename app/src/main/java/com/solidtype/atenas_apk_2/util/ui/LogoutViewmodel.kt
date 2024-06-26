package com.solidtype.atenas_apk_2.util.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.AuthCasos
import com.solidtype.atenas_apk_2.authentication.actualizacion.presentation.AuthEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LogoutViewmodel @Inject constructor(private val casos: AuthCasos):ViewModel() {

    fun onEvent(){
        viewModelScope.launch {  casos.logout() }
    }

}