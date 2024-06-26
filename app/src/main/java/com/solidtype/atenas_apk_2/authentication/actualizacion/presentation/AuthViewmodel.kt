package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.Authentication.actualizacion.presentation.AuthUIStates
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.AuthCasos
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AuthViewmodel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val casosAuth: AuthCasos
) : ViewModel() {

    var uiStates: MutableStateFlow<AuthUIStates> = MutableStateFlow(AuthUIStates())
        private set

    private val LICENCIA: String = "licencia"
    private val CORREO: String = "correo"
    private val recuerdame = context.getSharedPreferences("recuerdame", Context.MODE_PRIVATE)


    init {
        if(recuerdame.getString(CORREO, "") == ""){
            uiStates.update { it.copy(licenciaGuardada = false) }
        }else{
            uiStates.update { it.copy(licenciaGuardada = true) }
        }
        uiStates.update {
            it.copy(
                correoGuardado = recuerdame.getString(CORREO, "").toString()
            )
        }
        onEvent(AuthEvent.IsAutenticatedEvent)
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.IsAutenticatedEvent -> {
                uiStates.update { it.copy(isLoading = true) }
                if (isNetworkAvailable()) {
                    uiStates.update { it.copy(network = true) }
                    isAutenticated()
                } else {
                    viewModelScope.launch {
                        uiStates.update { it.copy(isLoading = false) }
                        delay(2000)
                        uiStates.update { it.copy(network = true) }
                        onEvent(AuthEvent.IsAutenticatedEvent)
                    }
                }
            }

            is AuthEvent.LoginEvent -> {
                uiStates.update { it.copy(isLoading = true) }
                if (isNetworkAvailable()) {
                    uiStates.update { it.copy(network = true) }

                    if (event.licencia.isNotBlank()) {
                        licencia(event.licencia)
                        login(event.email, event.password, event.licencia)

                    } else {
                       login(event.email, event.password,recuerdame.getString(LICENCIA, "").toString() )
                        println("Licencia temporal : ${recuerdame.getString(LICENCIA, "").toString()}")
                    }
                } else {
                    viewModelScope.launch {
                        delay(1000)
                        uiStates.update { it.copy(isLoading = false, network = false) }
                        delay(10000)
                        uiStates.update { it.copy(isLoading = true, network = true) }
                        onEvent(AuthEvent.IsAutenticatedEvent)
                    }
                }

            }

            is AuthEvent.Recuerdame -> {
                recuerdame(event.email)
            }

            is AuthEvent.EliminarRecuerdos -> {
                eliminarRecuerdos()
            }

            else -> {
                viewModelScope.launch { casosAuth.logout() }

            }
        }
    }

    private fun login(email: String, pass: String, licencia:String) {
        viewModelScope.launch {
            uiStates.update { it.copy(isLoading = true) }
            withContext(Dispatchers.IO) {
                razonesDe(casosAuth.login(email, pass, getDeviceId(), licencia))
                uiStates.update {
                    it.copy(
                        isAutenticated = casosAuth.whoIs(),
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun razonesDe(checkListAuth: CheckListAuth): Boolean {
        when (checkListAuth.autenticado) {
            true -> {
                when (checkListAuth.deviceRegistrado) {
                    true -> {
                        when (checkListAuth.licensiaActiva) {//REvision por usuarios de otra tablet.
                            true -> {
                                uiStates.update {
                                    it.copy(
                                        isAutenticated = Usuario(
                                            correo = checkListAuth.emailUsuario,
                                            tipoUser = checkListAuth.tipoUser
                                        ),
                                        razones = "Bienvenido usuario: ${checkListAuth.tipoUser}."
                                    )
                                }
                                return true
                            }
                            false -> {
                                eliminarLicencia()
                                logout()
                                uiStates.update {
                                    it.copy(
                                        isAutenticated = null,
                                        razones = "Licencia no activa.",
                                        licenciaGuardada = false
                                    )

                                }



                                return false
                            }
                        }
                    }

                    false -> {
                        uiStates.update {
                            it.copy(
                                isAutenticated = null,
                                razones = "Dispositivo no registrado."
                            )
                        }
                        eliminarLicencia()
                        logout()
                        return false
                    }
                }

            }

            false -> {
                uiStates.update {
                    it.copy(
                        isAutenticated = null,
                        razones = "Usuario no identificado"
                    )
                }
                return false
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            uiStates.update { it.copy(isLoading = true, isAutenticated = null) }
            casosAuth.logout()
            uiStates.update { it.copy(isLoading = false) }
        }
    }

    @SuppressLint("HardwareIds")
    private fun getDeviceId(): String? {
        val deviceId: String? = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )

        return deviceId
    }

    private fun isAutenticated() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                uiStates.update {
                    it.copy(isAutenticated = casosAuth.whoIs())
                }
                uiStates.update { it.copy(isLoading = false) }

            }
        }
    }

    private fun isNetworkAvailable(): Boolean {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun eliminarRecuerdos() {
        recuerdame
            .edit()
            .remove(CORREO)
            .apply()
    }

    private fun recuerdame(correo: String?) {
        recuerdame
            .edit()
            .putString(CORREO, correo)
            .apply()

    }

    private fun licencia(licencia: String?) {
        recuerdame
            .edit()
            .putString(LICENCIA, licencia)
            .apply()

    }

    private fun eliminarLicencia() {
        uiStates.update { it.copy(licenciaGuardada = false) }
        recuerdame
            .edit()
            .remove(LICENCIA)
            .apply()
    }
}