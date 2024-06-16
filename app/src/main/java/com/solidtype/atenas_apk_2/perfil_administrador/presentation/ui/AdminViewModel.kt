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
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class AdminViewModel @Inject constructor(private val casos: AdminUseCases): ViewModel() {

    private val _uiState:MutableStateFlow<PerfilUIState> = MutableStateFlow(PerfilUIState())
    val uiState: StateFlow<PerfilUIState> = _uiState.asStateFlow()

    init {
        perfilAdmin()
    }

    fun onEvent(evento: PerfilEvent){
        when(evento){
            PerfilEvent.VerPerfil -> {
                perfilAdmin()
            }
            is PerfilEvent.UpdatePerfil -> {
                viewModelScope.launch {casos.updateAdmin(evento.perfil)  }
            }else->{

            }
        }
    }
    private fun perfilAdmin(){
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            casos.getAdminInfo().collect { administradores ->
                _uiState.update { it.copy(perfilAdmin = administradores, isLoading = false) }
            }
        }
        }
    }
