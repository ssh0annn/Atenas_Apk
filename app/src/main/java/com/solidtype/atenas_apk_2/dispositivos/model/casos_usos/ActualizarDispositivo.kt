package com.solidtype.atenas_apk_2.dispositivos.model.casos_usos

import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.dispositivos.model.repository.DispositivosRepository
import javax.inject.Inject

class ActualizarDispositivo @Inject constructor(private val repo: DispositivosRepository) {
    suspend operator fun invoke(dispositivo: Dispositivo) = repo.updateDispositivo(dispositivo)
}