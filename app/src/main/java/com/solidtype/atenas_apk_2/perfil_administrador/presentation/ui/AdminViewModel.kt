package com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.AuthCasos
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.AdminUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val casos: AdminUseCases,
    private val casosAuthen: AuthCasos
) : ViewModel() {

    var uiState: MutableStateFlow<PerfilUIState> = MutableStateFlow(PerfilUIState())
        private set

    init {
        perfilAdmin()
    }

    fun onEvent(evento: PerfilEvent) {
        when (evento) {
            PerfilEvent.VerPerfil -> {
                perfilAdmin()
            }

            is PerfilEvent.UpdatePerfil -> {
                println("Este es el perfil ${evento.perfil}")
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {

                        casos.updateAdmin(evento.perfil)

                    }
                }
            }
            is PerfilEvent.ChangePassword -> {
                changePassword(evento)

            }
        }
    }
    private fun changePassword(eve: PerfilEvent.ChangePassword){
        uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            val cambiar:Boolean = casosAuthen.cambiarPassword(eve.email, eve.oldPassword, eve.newPassword)
            uiState.update {
                if(cambiar){

                    it.copy(isClaveCambiada =cambiar, passwordState = "Cambio Successfull!")
                }else{
                    it.copy(isClaveCambiada =cambiar, passwordState = "Intente de nuevo")
                }
            }
            delay(2000)
            uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
    private fun perfilAdmin() {
        uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            casos.getAdminInfo().collect { administradores ->
                uiState.update { it.copy(perfilAdmin = administradores, isLoading = false) }
            }
        }
    }
}
