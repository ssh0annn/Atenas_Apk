package com.solidtype.atenas_apk_2.users.presentation.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginViewModel: ViewModel() {

    private val _mail = MutableLiveData<String>()
    val mail: LiveData<String> = _mail

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChange(email: String, pass: String) {
        _mail.value = email
        _pass.value = pass
        _login.value = validarCamposEmail(email) && validarCamposPass(pass)
    }

    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }
    fun validateUser(user: String, pass: String): Boolean {
        //Lógica con firebase
        return user == "admin" && pass == "admin"
    }

    private fun validarCamposEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validarCamposPass(pass: String): Boolean = pass.length >= 8
}