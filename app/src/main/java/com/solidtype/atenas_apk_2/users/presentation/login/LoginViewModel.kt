package com.solidtype.atenas_apk_2.users.presentation.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _mail = MutableLiveData<String>()
    val mail: LiveData<String> = _mail

    fun validarLogin(user: String, pass: String): Boolean {
        return validarCamposUser(user) && validarCamposPass(pass) && validateUser(user, pass)
    }
    fun validateUser(user: String, pass: String): Boolean {
        return user == "admin" && pass == "admin"
    }

    fun validarCamposUser(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validarCamposPass(pass: String): Boolean {
        return pass.isNotEmpty() && pass.length >= 8
    }
}