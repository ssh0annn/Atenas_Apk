package com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.AdminUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

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
                println("Este es el perfil ${evento.perfil}")
                viewModelScope.launch {
                    withContext(Dispatchers.IO){

                            casos.updateAdmin(evento.perfil)

                    }
                }
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
