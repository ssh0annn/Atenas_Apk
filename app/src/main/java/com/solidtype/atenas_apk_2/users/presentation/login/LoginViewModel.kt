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

       val correo = _mail.value ?: ""
       val passw = _pass.value ?: ""
        viewModelScope.launch {
            println("Entro al viewModelScope")
            withContext(Dispatchers.Main){
                _isLoading.value = true
                println("Entro al withContext, y esta loading")
                if (validateUser(correo, passw)) {
                    println("Consulto validacion")
                    Toast.makeText(context,"Login correcto!!", Toast.LENGTH_SHORT).show()
                    println("Usuario y contraseña validos ${_mail.value!!}, ${_pass.value!!}")

                } else {
                    Toast.makeText(context,"Login incorrecto!!", Toast.LENGTH_SHORT).show()
                    println("Usuario o contraseña invalidos")


                }
            }
            println("loading debe ser false:${ _isLoading.value} ")
            println("voy a salir del viewModelScope")
        }
        println("sali del  viewModelScope")
        _isLoading.value = false
        println("loading debe ser false:${ _isLoading.value} ")

    }
   private suspend fun validateUser(user: String, pass: String): Boolean {
        //Lógica con firebase
       val casosigin =SignInUseCase()
       val resultado =casosigin(user,pass)
       if (resultado.successful){
           println("Success en validateUser: ${resultado.successful} ,<---")
           println("En caso de: ${resultado.errorMessage} <---deberia estar bacio")
           return true
       }else{
           println("error en validateUser: ${resultado.successful}, <---")
           return false
       }

    }

    private fun validarCamposEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches() // -> Boolean

    private fun validarCamposPass(pass: String) = pass.length >= 8 // -> Boolean
}