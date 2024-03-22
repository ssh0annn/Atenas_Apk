package com.solidtype.atenas_apk_2.products.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product_Table")
data class ProductEntity(
    @PrimaryKey val Code_Product : Int? = null,
    @ColumnInfo(name = "Name_Product") val Name_Product : String?,
    @ColumnInfo(name = "Description_Product") val Description_Product : String?,
    @ColumnInfo(name = "Category_Product") val Category_Product : String?,
    @ColumnInfo(name = "Price_Product") val Price_Product : Double?,
    @ColumnInfo(name = "Model_Product") val Model_Product : String?,
    @ColumnInfo(name = "Price_Vending_Product") val Price_Vending_Product : Double?,
    @ColumnInfo(name = "Tracemark_Product") val Tracemark_Product : String?,
    @ColumnInfo(name = "Count_Product") val Count_Product : Int?,
){
    constructor() : this(null, "", "", "", 0.0, "",0.0,"", 0)
}