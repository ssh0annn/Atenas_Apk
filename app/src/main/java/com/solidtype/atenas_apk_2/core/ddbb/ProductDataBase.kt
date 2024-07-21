package com.solidtype.atenas_apk_2.core.ddbb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.solidtype.atenas_apk_2.perfil_administrador.data.administradorDao
import com.solidtype.atenas_apk_2.dispositivos.data.ddbb.DispositivoDao
import com.solidtype.atenas_apk_2.products.data.local.dao.categoriaDao
import com.solidtype.atenas_apk_2.facturacion.data.local.dao.detalle_ventaDao
import com.solidtype.atenas_apk_2.products.data.local.dao.inventarioDao
import com.solidtype.atenas_apk_2.gestion_proveedores.data.personaDao
import com.solidtype.atenas_apk_2.gestion_usuarios.data.roll_usuarioDao
import com.solidtype.atenas_apk_2.servicios.data.servicioDao
import com.solidtype.atenas_apk_2.gestion_tickets.data.ticketDao
import com.solidtype.atenas_apk_2.core.daos.tipo_ventaDao
import com.solidtype.atenas_apk_2.dispositivos.model.Dispositivo
import com.solidtype.atenas_apk_2.gestion_usuarios.data.usuarioDao
import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
import com.solidtype.atenas_apk_2.historial_ventas.data.local.dao.actualizacion.ventaDao
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.facturacion.domain.model.detalle_venta
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.servicios.modelo.servicio
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta

@Database(entities = [
    categoria::class, detalle_venta::class, inventario::class, persona::class,
    roll_usuarios::class, servicio::class, ticket::class, tipo_venta::class,
    usuario::class, venta::class, administrador::class, Dispositivo::class
], version = 30, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ProductDataBase : RoomDatabase() {
    //nuevas bases de datos oficiales y centradas en el proyecto completas (version 1 de ellas)
    abstract val categoriaDAO: categoriaDao
    abstract val detalleVentaDAO: detalle_ventaDao
    abstract val inventarioDAO: inventarioDao
    abstract val personaDAO: personaDao
    abstract val rollUsuarioDAO: roll_usuarioDao
    abstract val servicioDAO: servicioDao
    abstract val ticketDAO: ticketDao
    abstract val tipoVentaDAO: tipo_ventaDao
    abstract val usuarioDAO: usuarioDao
    abstract val ventaDAO: ventaDao
    abstract val adminDao: administradorDao
    abstract val dispositivoDao: DispositivoDao
//    companion object{
//        @Volatile
//        private var DDBB: ProductDataBase? = null
//        fun getDataBase(context: Context):ProductDataBase {
//            return DDBB ?: synchronized(this) {
//                Room.databaseBuilder(context, ProductDataBase::class.java, "Atenas_Database")
//                    .fallbackToDestructiveMigration()
//                    .build()
//                    .also { DDBB = it }
//            }
//        }
//    }
}