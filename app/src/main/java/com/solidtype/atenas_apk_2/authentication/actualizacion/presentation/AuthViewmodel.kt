package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
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
    private var conteoConexion = false


    init {
        if (recuerdame.getString(CORREO, "") == "") {
            uiStates.update { it.copy(licenciaGuardada = false) }
        } else {
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
            AuthEvent.IsAutenticatedEvent -> handleAuthenticationEvent()
            is AuthEvent.LoginEvent -> handleLoginEvent(event)
            is AuthEvent.Recuerdame -> recuerdame(event.email)
            is AuthEvent.EliminarRecuerdos -> eliminarRecuerdos()
            is AuthEvent.ForgetPassword -> forgetPassword(event.email)
            AuthEvent.RegistrarNuevoDevice -> registraNuevoDivice(getDeviceId().toString())
            AuthEvent.CancelarRegistro -> {
                uiStates.update { it.copy(dispositivo = true)  }
                eliminarLicencia()
                logout()
            }
            else -> viewModelScope.launch { casosAuth.logout() }
        }
    }

    private fun registraNuevoDivice(id:String){
        viewModelScope.launch {
            casosAuth.nuevoDevice(id, recuerdame.getString(LICENCIA, "").toString())
            uiStates.update { it.copy(dispositivo = true) }
        }
    }
    private fun handleAuthenticationEvent() {
        uiStates.update { it.copy(isLoading = true) }
        if (isNetworkAvailable()) {
            uiStates.update { it.copy(network = true) }
            conteoConexion()
            isAutenticated()
        } else {
            viewModelScope.launch {
                uiStates.update { it.copy(isLoading = false) }
                conteoConexion = true
                delay(2000)
                uiStates.update { it.copy(network = true) }
                onEvent(AuthEvent.IsAutenticatedEvent)
            }
        }
    }
    private fun handleLoginEvent(event: AuthEvent.LoginEvent) {
        uiStates.update { it.copy(isLoading = true) }
        if (isNetworkAvailable()) {
            uiStates.update { it.copy(network = true) }
            conteoConexion()
            val licencia = if (event.licencia.isNotBlank()) {
                licencia(event.licencia)
                event.licencia
            } else {
                recuerdame.getString(LICENCIA, "").toString()
            }
            login(event.email, event.password, licencia)

        } else {
            viewModelScope.launch {
                delay(1000)
                uiStates.update {
                    it.copy(
                        isLoading = false,
                        network = false,
                        razones = "No hay conexion..."
                    )
                }
                conteoConexion = true
                delay(10000)
                uiStates.update { it.copy(isLoading = true, network = true) }
                onEvent(AuthEvent.IsAutenticatedEvent)
            }
        }
    }
    private fun conteoConexion() {
        if (conteoConexion) {
            uiStates.update { it.copy(razones = "Conexion restablecida!") }
            conteoConexion = false
        }
    }
    fun limpiaRazones() {
        uiStates.update {
            it.copy(
                razones = null
            )
        }
    }

    private fun forgetPassword(email: String) {
        viewModelScope.launch {
            casosAuth.forgotPassword(email){success, cancel, execption ->
                println("""
                    success :$success, 
                    cancel:$cancel, 
                    execption: $execption
                """.trimIndent())
                if(success){
                    uiStates.update {
                        it.copy(enviado = true, razones = "Correo enviado!")
                        }
                } else {
                    uiStates.update {
                        it.copy(enviado = cancel, razones = execption)
                    }
                }

            }
        }
    }

    private fun login(email: String, pass: String, licencia: String) {
        viewModelScope.launch {
            uiStates.update { it.copy(isLoading = true) }
            withContext(Dispatchers.IO) {
                razonesDe(casosAuth.login(email, pass, getDeviceId(), licencia))
                uiStates.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun razonesDe(checkListAuth: CheckListAuth): Boolean {
        return when {
            !checkListAuth.autenticado -> {
                uiStates.update {
                    it.copy(
                        isAutenticated = null,
                        razones = "Usuario no identificado" ,
                        licenciaGuardada = false
                    )
                }
                false
            }
            !checkListAuth.licensiaActiva -> {
                uiStates.update {
                    it.copy(
                        isAutenticated = null,
                        razones = "Licencia no valida.",
                        licenciaGuardada = false
                    )
                }
                eliminarLicencia()
                logout()
                false
            }
            !checkListAuth.deviceRegistrado -> {
                uiStates.update {
                    it.copy(
                        isAutenticated = null,
                        razones = "Dispositivo no registrado: '${getDeviceId()}' ",
                        licenciaGuardada = false,
                        dispositivo = false
                    )
                }
                false
            }
            checkListAuth.tipoUser != TipoUser.UNKNOWN ->{
                uiStates.update {
                    it.copy(
                        isAutenticated = Usuario(
                            correo = checkListAuth.emailUsuario,
                            tipoUser = checkListAuth.tipoUser
                        ),
                        razones = "Bienvenido usuario: ${checkListAuth.emailUsuario}."
                    )
                }
                true
            }
            else -> {
                uiStates.update {
                    it.copy(
                        isAutenticated = null,
                        razones = "No autorizado!",
                        licenciaGuardada = false,
                        dispositivo = false
                    )
                }
                eliminarLicencia()
                logout()
                false
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
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
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