package com.solidtype.atenas_apk_2.users.presentation.login

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.users.domain.userCase.All_useCases
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val casos_uso:All_useCases= All_useCases(),

   ): ViewModel() {

    private val _logeado = mutableStateOf(LoginStates(verificado = false, autenticado = false))
    var logeado: State<LoginStates> = _logeado

    private val _mail = MutableLiveData<String>()
    var mail: LiveData<String> = _mail

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    private val _verificado = MutableLiveData<Boolean>()
    val verificado: LiveData<Boolean> = _verificado


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        var usuario:String?
        viewModelScope.launch {
            if(casos_uso.current_user() != null){
                usuario=casos_uso.current_user()!!.email.toString()
                var uid =casos_uso.current_user()!!.uid
                print("Este es el uid: $uid")
                cambiaEstadosVerificado()
                println("Usuario existente: $usuario")
            }
        }
    }
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

            if (validateUser(correo, passw)) {//ESTO ESTA MAL LA LOGICA DE NEGOCIO NO DEBE IR EN EL VIEWMODEL

                if(logicaNegocio()) {//logicaNegocio()
                        println("Entro al withContext, y esta loading")
                        _logeado.value=logeado.value.copy(autenticado = true, verificado = true)
                        cambiaEstadosVerificado()
                        println("Consulto validacion")
                        Toast.makeText(context,"Login correcto!!", Toast.LENGTH_SHORT).show()
                        println("Usuario y contraseña validos ${_mail.value!!}, ${_pass.value!!}")

            println("loading debe ser false:${ _isLoading.value}")
            println("voy a salir del viewModelScope")
        }else{
                    _isLoading.postValue(false)
                }
        } else {
                Toast.makeText(context,"Login incorrecto!!", Toast.LENGTH_SHORT).show()
                println("Usuario o contraseña invalidos")

            }
        println("sali del  viewModelScope")

            println(" al terminar todo (${_verificado.value},${ _logeado.value}) <---")
       }

        println("valor del login:${ _logeado.value} <--, valor del validated: ${_verificado.value} <---")

    }
   private suspend fun validateUser(user: String, pass: String): Boolean {
        //Lógica con firebase
       val resultado =casos_uso.login(user,pass)
       if (resultado.successful){
           println("Success en validateUser: ${resultado.successful} ,<---")
           println("En caso de: ${resultado.errorMessage} <---deberia estar bacio")
           return true
       }else{
           println("error en validateUser: ${resultado.successful}, <---")
           return false
       }

    }
    private suspend fun  logicaNegocio()= withContext(Dispatchers.Main){
                try{
                            //capturamos iccid
                            val getICCID=casos_uso.capturaIccid()
                            if(getICCID.isNotBlank()){
                                //. validamos en fireStore sus existencia. &&  validamos estado de la licencia

                                return@withContext casos_uso.validarICCID(getICCID) && casos_uso.estado_licencia(getICCID)
                            }else{
                                return@withContext false
                            }

                    }catch(logica:Exception){
                            casos_uso.logout()
                         return@withContext false
                    }
                }

         private fun cambiaEstadosVerificado(){
             this._verificado.value =true
         }
    }

    private fun validarCamposEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches() // -> Boolean

    private fun validarCamposPass(pass: String) = pass.length >= 8 // -> Boolean