package com.solidtype.atenas_apk_2.products.presentation.inventory

import android.os.Message
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

data class ProductosViewStates(

    val isLoading: Boolean = false,
    val products: List<inventario> = listOf(),
    val getOneProduct:Int=0,
    val pathExcel: String? = "",
    val userMessages: List<Message> = listOf(),
    val uriPath:String="",
    val errorMessages: String = ""


)

data class NewsItemUiState(
    val title: String,
    val body: String,
    val bookmarked: Boolean = false,

)

