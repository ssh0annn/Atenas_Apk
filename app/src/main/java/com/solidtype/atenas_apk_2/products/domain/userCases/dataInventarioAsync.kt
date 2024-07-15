package com.solidtype.atenas_apk_2.products.domain.userCases

import com.solidtype.atenas_apk_2.products.data.remote.remoteProFB.mediator.mediatorInventario
import javax.inject.Inject

class dataInventarioAsync @Inject constructor(
    private val mediatorInventario: mediatorInventario
) {
     suspend operator fun  invoke(){
        mediatorInventario.syncInventario()
    }
}