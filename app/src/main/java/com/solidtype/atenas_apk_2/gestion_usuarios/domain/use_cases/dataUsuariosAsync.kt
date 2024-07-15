package com.solidtype.atenas_apk_2.gestion_usuarios.domain.use_cases

import com.solidtype.atenas_apk_2.gestion_usuarios.data.remote.mediadorUsuario
import javax.inject.Inject

class dataUsuariosAsync @Inject constructor(
    private val mediadorUsuario: mediadorUsuario
) {
    suspend operator fun invoke() {
        mediadorUsuario.syncUsuario()
    }
}