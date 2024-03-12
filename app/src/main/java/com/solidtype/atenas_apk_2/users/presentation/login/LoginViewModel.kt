package com.solidtype.atenas_apk_2.users.presentation.login

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.users.domain.userCase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun onLoginSelected(context: Context) {

       var correo= _mail.value ?: " "
       var passw=_pass.value ?: " "
        viewModelScope.launch {

            withContext(Dispatchers.Main){
                _isLoading.value = true

                if (validateUser(correo, passw)) {

                    Toast.makeText(context,"Login correcto!!", Toast.LENGTH_SHORT).show()
                    println("Usuario y contraseña validos ${_mail.value!!}, ${_pass.value!!}")

                } else {
                    Toast.makeText(context,"Login incorrecto!!", Toast.LENGTH_SHORT).show()
                    println("Usuario o contraseña invalidos")

                }

            }
            _isLoading.value = false


        }



    }
   private suspend fun validateUser(user: String, pass: String): Boolean {
        //Lógica con firebase
            val casosigin =SignInUseCase()
            casosigin(user,pass)

        return false
    }

    private fun validarCamposEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun validarCamposPass(pass: String): Boolean = pass.length >= 8
}