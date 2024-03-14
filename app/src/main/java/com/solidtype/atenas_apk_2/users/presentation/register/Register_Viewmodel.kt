package com.solidtype.atenas_apk_2.users.presentation.register

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solidtype.atenas_apk_2.users.domain.userCase.implementados.Registrarse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class login_medenview (private val caso_uso: Registrarse = Registrarse()): ViewModel(){

    fun validar ( name:String, sim:String, apellido:String, correo:String,
                  nnegocio:String,dnegocio:String, telefono:String, password:String, confirmar:String, context: Context){
        if (name.isBlank() || apellido.isBlank() || correo.isBlank() || nnegocio.isBlank() ||
            dnegocio.isBlank() || telefono.isBlank() || password.isBlank() || confirmar.isBlank()

        ) {
            // Alguno de los campos está vacío, puedes manejar este caso según tus necesidades
            // Por ejemplo, mostrar un mensaje de error o realizar alguna acción.
            Toast.makeText(context, "Campos Vacios.", Toast.LENGTH_LONG).show()
        } else if (sim.length < 20){
            // caracteres
            android.widget.Toast.makeText(context, "ICCID debe tener 20 caracteres", android.widget.Toast.LENGTH_LONG).show()
        } else if (!isValidEmail(correo)) {
            Toast.makeText(context, "Correo electrónico no válido.", Toast.LENGTH_LONG).show()
        } else if (password.length < 7) {
            // caracteres
            android.widget.Toast.makeText(context, "La contraseña debe tener 8 caracteres", android.widget.Toast.LENGTH_LONG).show()
        } else if ( password != confirmar ){
            // Las contraseñas no coinciden
            Toast.makeText(context, "La contraseña no coinciden.", Toast.LENGTH_LONG).show()
        }  else {
            // Todos los campos están completos y las contraseñas coinciden
            // Puedes realizar alguna acción aquí, como iniciar session.
            //Ejemplo de prueba
            //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++s++
            println("Antes de la corrutina: ${listOf(correo, password,name, sim, apellido, nnegocio, dnegocio, telefono)}")
            viewModelScope.launch{
                println("iniciando la corrutina")
                withContext(Dispatchers.Main) {
                val autenticacion:Boolean=caso_uso(correo, password,name, sim, apellido, nnegocio, dnegocio, telefono)
                println("Dentro de withContext")
                    if(autenticacion){
                        Toast.makeText(context, "Inicio de seccion:  $correo, $password.", Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(context, "LA MACATE: $correo, $password", Toast.LENGTH_LONG).show()
                    }

                }
            }

            println("Ddatos en registro: ${listOf(correo, password,name, sim, apellido, nnegocio, dnegocio, telefono)}")
            println("Despues de la corrutina")

            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        }
    }
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }
}