package com.solidtype.atenas_apk_2.users.presentation.login

import android.util.Patterns
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

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