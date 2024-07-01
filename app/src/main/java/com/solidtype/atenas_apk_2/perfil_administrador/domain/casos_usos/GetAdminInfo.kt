package com.solidtype.atenas_apk_2.perfil_administrador.domain.casos_usos

import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.toVerInfoAdmin
import com.solidtype.atenas_apk_2.perfil_administrador.domain.repository.PerfilAdminRepository
import com.solidtype.atenas_apk_2.perfil_administrador.presentation.modelo.VerInfoAdmin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAdminInfo @Inject constructor(private val repo: PerfilAdminRepository) {

    operator fun invoke(): Flow<List<VerInfoAdmin?>> {
        return repo.getAdminInfo().map { lista ->
            lista.map {  it.toVerInfoAdmin() }
           }
    }
}