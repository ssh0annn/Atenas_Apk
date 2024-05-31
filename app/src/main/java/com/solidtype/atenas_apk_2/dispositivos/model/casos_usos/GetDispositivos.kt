package com.solidtype.atenas_apk_2.dispositivos.model.casos_usos

import com.solidtype.atenas_apk_2.dispositivos.model.repository.DispositivosRepository
import javax.inject.Inject

class GetDispositivos @Inject constructor(private val repo:DispositivosRepository) {

    operator fun invoke() = repo.getDispositivos()
}