package com.solidtype.atenas_apk_2.products.presentation.ejemploPresentacion

import android.os.Message
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity

data class ProductosViewStates(

    val isLoading: Boolean = false,
    val products: List<ProductEntity> = listOf(),
    val pathExcel: String? = "",
    val userMessages: List<Message> = listOf()


)

data class NewsItemUiState(
    val title: String,
    val body: String,
    val bookmarked: Boolean = false,

)

