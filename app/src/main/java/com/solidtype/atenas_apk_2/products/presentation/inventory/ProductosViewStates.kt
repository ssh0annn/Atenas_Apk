package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.net.Uri
import android.os.Message
import com.solidtype.atenas_apk_2.gestion_proveedores.presentation.cliente.modelo.Personastodas
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

data class ProductosViewStates(

    val isLoading: Boolean = false,
    val products: List<inventario> = listOf(),
    val getOneProduct:Int=0,
    val pathExcel: String? = "",
    val userMessages: List<Message> = listOf(),
    val uriPath: Uri? = null,
    val messages: String = "",
    val categoria:List<categoria> = emptyList(),
    val proveedores:List<Personastodas.Proveedor> = emptyList()


)


