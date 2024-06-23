package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.authentication.actualizacion.data.modelo.CheckListAuth
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.AuthCasos
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
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

   private val recuerdame = context.getSharedPreferences("recuerdame", Context.MODE_PRIVATE)

    init {

        uiStates.update { it.copy(network = isNetworkAvailable(),
            correoGuardado = recuerdame.getString("correo", "").toString()
            ) }
        if(isNetworkAvailable()){
            isAutenticated()
        }else{
            uiStates.update { it.copy(isAutenticated = null) }
        }

    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.IsAutenticatedEvent -> {
                isAutenticated()
                uiStates.update { it.copy(network = isNetworkAvailable()) }
            }

            is AuthEvent.LoginEvent -> {
                uiStates.update { it.copy(network = isNetworkAvailable()) }
                login(event.email, event.password)
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

    private fun login(email: String, pass: String) {
        viewModelScope.launch {
            uiStates.update { it.copy(isLoading = true) }
            withContext(Dispatchers.IO) {
                razonesDe(casosAuth.login(email, pass, getDeviceId()))
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
         when(checkListAuth.autenticado){
              true -> {
                  when(checkListAuth.deviceRegistrado){
                        true -> {
                            when(checkListAuth.licensiaActiva){//REvision por usuarios de otra tablet.
                                true ->{
                                    uiStates.update { it.copy(isAutenticated = Usuario(
                                        correo = checkListAuth.emailUsuario,
                                        tipoUser = checkListAuth.tipoUser
                                    ), razones = "Bienvenido usuario: ${checkListAuth.tipoUser}.") }
                                    return true
                                }
                                false -> {
                                    uiStates.update { it.copy(isAutenticated = null, razones = "Licencia no activa.") }
                                    return false
                                }
                            }
                        }
                        false -> {
                            uiStates.update { it.copy(isAutenticated = null, razones = "Dispositivo no registrado.") }
                            return false
                        }
                    }
              }
              false -> {
                  uiStates.update { it.copy(isAutenticated = null, razones = "Usuario no identificado") }
                  return false
              }
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
                if(isNetworkAvailable()){
                    isAutenticated()
                    uiStates.update {
                        it.copy(isAutenticated = casosAuth.whoIs())
                    }
                }else{
                    uiStates.update { it.copy(isAutenticated = null) }
                }

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
    private fun eliminarRecuerdos(){
        recuerdame
            .edit()
            .remove("correo")
            .apply()
    }
    private fun recuerdame(correo: String?){
        recuerdame
            .edit()
            .putString("correo", correo)
            .apply()

    }
}