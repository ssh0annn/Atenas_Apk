package com.solidtype.atenas_apk_2.perfil_administrador.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.AuthCasos
import com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos.AdminUseCases
import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
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
                        uiState.update { it.copy(isClaveCambiada = false, mensaje = "Perfil Actualizado!") }
                        casos.updateAdmin(evento.perfil)

                    }
                }
            }

            is PerfilEvent.ChangePassword -> {
                changePassword(evento)

            }

            PerfilEvent.CleanMensaje -> {
                uiState.update {
                    it.copy(mensaje = null)
                }

            }
        }
    }

    private fun changePassword(eve: PerfilEvent.ChangePassword) {
        uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {

                casosAuthen.cambiarPassword(eve.email, eve.oldPassword, eve.newPassword) { success, reason ->
                    uiState.update {
                            it.copy(
                                isClaveCambiada = success,
                                passwordState = reason ?: "",
                                showDialogo = !success,
                                mensaje = if(success) "Clave cambiada con exito!!" else reason
                            )
                    }
                    if(success){
                        val per =uiState.value.perfilAdmin.first()
                        per?.let { p ->
                            onEvent(PerfilEvent.UpdatePerfil(
                                administrador(
                                    id_administrador = p.id_administrador,
                                    nombre = p.nombre,
                                    apellido = p.apellido,
                                    correo = p.correo,
                                    telefono =p.telefono,
                                    direccion_negocio =p.direccion_negocio,
                                    nombre_negocio = p.nombre_negocio,
                                    clave = eve.newPassword,
                                    licencia = p.licencia,
                                    fecha_compra =p.fecha_compra,
                                    fecha_caduca = p.fecha_caduca,
                                    estado = p.estado
                                )))
                        }
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
