package com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_usuario

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.UsuarioUseCases
import javax.inject.Inject

class GetCurrentUserEmail @Inject constructor(private val usuarioCasos: UsuarioUseCases) {


    /*
    Aqui debemos espesificar el usuario logeado
    Esta funcion es temporal para resolver u problema, Pero debe ser refactorizada.
     */
    suspend fun getUser():Long {
        val usuarios = usuarioCasos.buscarUsuario("adderlis@yahoo.com")

        var usuario: usuario? = null
            usuarios.collect{ lista ->
            lista.forEach {
                if(it.email == "adderlis@yahoo.com") {
                usuario = it
                }
            }
        }
        return usuario!!.id_usuario
    }
}