package com.solidtype.atenas_apk_2.users.presentation.register

import android.content.Context
import android.widget.Toast

import androidx.lifecycle.ViewModel
import com.solidtype.atenas_apk_2.users.domain.userCase.SignUpUseCase
import com.solidtype.atenas_apk_2.users.domain.userCase.severino.Registrarse


class login_medenview (private val caso_uso: Registrarse= Registrarse()): ViewModel(){

    fun validar ( name:String, sim:Int, apellido:String, correo:String,
                  nnegocio:String,dnegocio:String, telefono:Int, password:String, confirmar:String, context: Context){
        if (name.isBlank() || apellido.isBlank() || correo.isBlank() || nnegocio.isBlank() ||

            dnegocio.isBlank() || telefono == 0 || password.isBlank() || confirmar.isBlank()
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
            var phone:String =telefono.toString()
            var usercase= SignUpUseCase()
            var id :String = sim.toString()
            var autenticacion:Boolean=caso_uso(correo, password)

            if(autenticacion){
                        Toast.makeText(context, "Inicio de seccion:  $correo, $password.", Toast.LENGTH_LONG).show()

            }else{
                Toast.makeText(context, "LA MACATE: $correo, $password", Toast.LENGTH_LONG).show()
            }

            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        }
    }
}