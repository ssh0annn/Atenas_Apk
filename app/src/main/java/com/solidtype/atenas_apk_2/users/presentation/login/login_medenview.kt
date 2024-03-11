package com.solidtype.atenas_apk_2.users.presentation.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.users.data.repository.RepositoryImpl
import com.solidtype.atenas_apk_2.users.domain.model.UserModel
import com.solidtype.atenas_apk_2.users.domain.repository.UserRepository
import com.solidtype.atenas_apk_2.users.domain.userCase.SignInUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.SignUpUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.severino.Registrarse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class login_medenview (private val caso_uso: Registrarse= Registrarse()): ViewModel(){

    fun validar ( name:String, sim:String, apellido:String, correo:String,
                  nnegocio:String,dnegocio:String, telefono:String, password:String, confirmar:String, context: Context){
        if (name.isBlank() || apellido.isBlank() || correo.isBlank() || nnegocio.isBlank() ||
            dnegocio.isBlank() || telefono.isBlank() || password.isBlank() || confirmar.isBlank()

        ) {
            // Alguno de los campos está vacío, puedes manejar este caso según tus necesidades
            // Por ejemplo, mostrar un mensaje de error o realizar alguna acción.
            Toast.makeText(context, "Campos Vacios.", Toast.LENGTH_LONG).show()
        } else if (password != confirmar) {
            // Las contraseñas no coinciden
            //

            Toast.makeText(context, "La contraseña no coinciden.", Toast.LENGTH_LONG).show()
        } else {
            // Todos los campos están completos y las contraseñas coinciden
            // Puedes realizar alguna acción aquí, como iniciar sesión.
            //Ejemplo de prueba
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            val autenticacion:Boolean=caso_uso(correo, password,name, sim, apellido, nnegocio, dnegocio, telefono)

            if(autenticacion){
                        Toast.makeText(context, "Inicio de seccion:  $correo, $password.", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(context, "LA MACATE: $correo, $password", Toast.LENGTH_LONG).show()
            }

            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        }
    }
}