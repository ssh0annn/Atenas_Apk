package com.solidtype.atenas_apk_2.products.domain.userCases

import android.net.Uri
import com.solidtype.atenas_apk_2.products.domain.repository.InventarioRepo
import javax.inject.Inject

class ImportarExcelFile @Inject constructor(private val repo: InventarioRepo) {

    suspend operator fun invoke(path:Uri)=repo.importarExcel(path)
}