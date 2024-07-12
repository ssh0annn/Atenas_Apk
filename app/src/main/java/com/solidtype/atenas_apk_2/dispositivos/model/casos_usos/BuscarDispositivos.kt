package com.solidtype.atenas_apk_2.dispositivos.model.casos_usos


import com.solidtype.atenas_apk_2.dispositivos.model.repository.DispositivosRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BuscarDispositivos  @Inject constructor(private val repo: DispositivosRepository) {
    operator fun invoke(any: String) = repo.buscarDispositivoCaracter(any).map { it.filter { it.estado == true } }
}