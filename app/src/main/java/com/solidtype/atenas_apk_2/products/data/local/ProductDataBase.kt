package com.solidtype.atenas_apk_2.products.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductDataBase : RoomDatabase() {
    abstract val getProductDao :ProductDao
}