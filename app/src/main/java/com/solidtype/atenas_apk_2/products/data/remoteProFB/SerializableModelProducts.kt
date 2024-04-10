package com.solidtype.atenas_apk_2.products.data.remoteProFB

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
private val json = Json


@Serializable
data class SerializableModelProducts(
val code_product: Int? = null ,
val Description_Product : String? = null,
val Category_Product : String? = null ,
val Price_Product : Double? = null ,
val Model_Product : String? = null ,
val Price_Vending_Product : Double? = null,
val Tracemark_Product : String? = null,
val Count_Product : Int? = null,
)
