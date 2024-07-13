package com.solidtype.atenas_apk_2.servicios.modelo.casos_usos.manage_usuario

import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases.UsuarioUseCases
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserEmail @Inject constructor(private val usuarioCasos: UsuarioUseCases,
 ) {


    /*
    Aqui debemos espesificar el usuario logeado
    Esta funcion es temporal para resolver u problema, Pero debe ser refactorizada.
     */
     fun getUser(correo :String): Flow<List<usuario>> {
        return usuarioCasos.buscarUsuario(correo, true)
    }
}