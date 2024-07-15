package com.solidtype.atenas_apk_2.authentication.actualizacion.domain.casos_usos

import com.solidtype.atenas_apk_2.perfil_administrador.data.mediadorAdmin.mediadorAdmin
import javax.inject.Inject

class DatosAdmin @Inject constructor(
    private val mediador: mediadorAdmin
) {
    operator suspend fun invoke (){
        mediador.syncDataAdmin()
    }

}