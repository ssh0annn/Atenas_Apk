package com.solidtype.atenas_apk_2.products.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialVentaDAO
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity

@Database(entities = [ProductEntity::class,HistorialVentaEntidad::class], version = 2, exportSchema = false)
abstract class ProductDataBase : RoomDatabase() {
    abstract val ProductDao :ProductDao
    abstract val HistorialDao :HistorialVentaDAO
}