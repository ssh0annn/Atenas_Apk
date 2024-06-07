package com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.AdminUseCases
import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.toAdministrador
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(private val casos: AdminUseCases): ViewModel() {

    var uiState:MutableStateFlow<PerfilUIState> = MutableStateFlow(PerfilUIState())
        private set

    init {
        perfilAdmin()
    }

    fun onEvent(evento: PerfilEvent){
        when(evento){
            is PerfilEvent.VerPerfil -> {
                perfilAdmin()
            }
            is PerfilEvent.UpdatePerfil -> {
                viewModelScope.launch {casos.updateAdmin(evento.perfil)  }
            }
        }
    }
    private fun perfilAdmin(){
        uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
           casos.getAdminInfo().collect{ administradores ->
                uiState.update { it.copy(perfilAdmin = administradores, isLoading = false)}
            }

        }
    }
}