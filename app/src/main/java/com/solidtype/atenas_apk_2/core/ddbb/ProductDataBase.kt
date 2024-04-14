package com.solidtype.atenas_apk_2.core.ddbb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialTicketDAO
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.HistorialVentaDAO
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialTicketEntidad
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.HistorialVentaEntidad
import com.solidtype.atenas_apk_2.products.data.local.dao.ProductDao
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity

@Database(entities = [ProductEntity::class,HistorialVentaEntidad::class,HistorialTicketEntidad::class], version = 5, exportSchema = false)

abstract class ProductDataBase : RoomDatabase() {
    abstract val ProductDao :ProductDao
    abstract val HistorialVentaDao :HistorialVentaDAO
    abstract val HistorialTicketDao :HistorialTicketDAO
}