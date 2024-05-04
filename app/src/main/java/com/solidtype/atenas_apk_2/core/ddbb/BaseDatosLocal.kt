package com.solidtype.atenas_apk_2.core.ddbb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solidtype.atenas_apk_2.core.daos.categoriaDao
import com.solidtype.atenas_apk_2.core.daos.detalle_ticketDao
import com.solidtype.atenas_apk_2.core.daos.detalle_ventaDao
import com.solidtype.atenas_apk_2.core.daos.inventarioDao
import com.solidtype.atenas_apk_2.core.daos.personaDao
import com.solidtype.atenas_apk_2.core.daos.roll_usuarioDao
import com.solidtype.atenas_apk_2.core.daos.servicioDao
import com.solidtype.atenas_apk_2.core.daos.ticketDao
import com.solidtype.atenas_apk_2.core.daos.tipo_ventaDao
import com.solidtype.atenas_apk_2.core.daos.usuarioDao
import com.solidtype.atenas_apk_2.core.daos.ventaDao
import com.solidtype.atenas_apk_2.core.entidades.categoria
import com.solidtype.atenas_apk_2.core.entidades.detalle_ticket
import com.solidtype.atenas_apk_2.core.entidades.detalle_venta
import com.solidtype.atenas_apk_2.core.entidades.inventario
import com.solidtype.atenas_apk_2.core.entidades.persona
import com.solidtype.atenas_apk_2.core.entidades.roll_usuarios
import com.solidtype.atenas_apk_2.core.entidades.servicio
import com.solidtype.atenas_apk_2.core.entidades.ticket
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.core.entidades.usuario
import com.solidtype.atenas_apk_2.core.entidades.venta

@Database(entities = [
    categoria::class, detalle_ticket::class, detalle_venta::class, inventario::class,
    persona::class, roll_usuarios::class, servicio::class, ticket::class,
    tipo_venta::class, usuario::class, venta::class
                     ], version = 1)
abstract class BaseDatosLocal : RoomDatabase() {
    abstract fun categoriaDAO():categoriaDao
    abstract fun detalleTicketDAO():detalle_ticketDao
    abstract fun detalleVentaDAO():detalle_ventaDao
    abstract fun inventarioDAO():inventarioDao
    abstract fun personaDAO():personaDao
    abstract fun rollUsuarioDAO():roll_usuarioDao
    abstract fun servicioDAO():servicioDao
    abstract fun ticketDAO():ticketDao
    abstract fun tipoVentaDAO():tipo_ventaDao
    abstract fun usuarioDAO():usuarioDao
    abstract fun ventaDAO():ventaDao
}