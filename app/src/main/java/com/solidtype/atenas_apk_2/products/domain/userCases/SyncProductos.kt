package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import javax.inject.Inject

class SyncProductos @Inject constructor(private val repo: InventarioRepo) {

    suspend operator fun invoke() = repo.syncronizacionProductos()

}