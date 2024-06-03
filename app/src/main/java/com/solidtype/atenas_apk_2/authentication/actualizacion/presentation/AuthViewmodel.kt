package com.solidtype.atenas_apk_2.authentication.actualizacion.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.TipoUser
import com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos.AuthCasos
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
):ViewModel(){

    var uiStates:MutableStateFlow<AuthUIStates> = MutableStateFlow(AuthUIStates())
        private set

    init {

        uiStates.update { it.copy(network = isNetworkAvailable())}
        isAutenticated()
    }

    fun onEvent(event:AuthEvent){
        when(event){
            AuthEvent.IsAutenticatedEvent -> {
                isAutenticated()
            }
            is AuthEvent.LoginEvent -> {
                login(event.email, event.password)
            }
            else -> {

            }
        }
    }

    private fun login(email:String, pass:String){
        viewModelScope.launch {
            uiStates.update { it.copy(isLoading = true)}
                withContext(Dispatchers.IO) {
                if(casosAuth.login(email, pass, getDeviceId())){
                    uiStates.update { it.copy(isAutenticated = casosAuth.whoIs(), isLoading = false) }
                }else{
                    uiStates.update { it.copy(isAutenticated = casosAuth.whoIs(), isLoading = false) }
                }
            }
        }

    }

    @SuppressLint("HardwareIds")
    private  fun getDeviceId(): String? {
        val deviceId: String? = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )

        return deviceId
    }
    private fun isAutenticated(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                uiStates.update {
                    it.copy(isAutenticated = casosAuth.whoIs() )
                }
            }
        }
    }


    private fun isNetworkAvailable(): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}