package com.solidtype.atenas_apk_2.products.data.remoteProFB

import kotlinx.serialization.Serializable

@Serializable
data class SerializableModelProducts(
val code_product: Int ,
val Description_Product : String ,
val Category_Product : String ,
val Price_Product : Double ,
val Model_Product : String ,
val Price_Vending_Product : Double,
val Tracemark_Product : String,
val Count_Product : Int,

)
