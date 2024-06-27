package com.solidtype.atenas_apk_2.core.transacciones.daoTransacciones

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.DetalleVentaRelation
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.InventarioModeloRelation
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.TicketModeloRelation
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.UsuariosRelation
import com.solidtype.atenas_apk_2.core.transacciones.modeloTransacciones.VentaRelationTransacciones
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_proveedores.data.persona
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.roll_usuarios
import com.solidtype.atenas_apk_2.gestion_usuarios.domain.modelo.usuario
import com.solidtype.atenas_apk_2.historial_ventas.domain.model.actualizacion.venta
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario

@Dao
interface DaoTransacciones {
    //Funciones tickets
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTipoVenta(tipoVenta: tipo_venta)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTicket(ticket : ticket)

    @Update
    suspend fun updateTicket(ticket: ticket)

    //Transactions tickets
    @Transaction
    @Query("SELECT * FROM ticket ")
    suspend fun getAllTickets(): List<TicketModeloRelation>

    @Transaction
    suspend fun crearTicket(ticketTransaction: TicketModeloRelation){
        insertarTipoVenta(ticketTransaction.tipo_venta)
        addTicket(ticketTransaction.ticket)
    }

    @Transaction
    suspend fun deleteTicket(ticket: ticket){
        ticket.estado = false
        updateTicket(ticket)
    }


    //Funciones Inventario
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategia(categoria: categoria)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersona(provedoor: persona)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInventario(inventario: inventario)

    @Transaction
    suspend fun crearInventario(addInventario: InventarioModeloRelation){
        addCategia(addInventario.categoria)
        addPersona(addInventario.provedor)
        addInventario(addInventario.inventario)
    }

    @Transaction
    @Query("SELECT * FROM inventario")
    suspend fun getAllInventario(): List<InventarioModeloRelation>


    //funciones venta
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addvendedor(vendedor: usuario)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCliente(cliente: persona)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addventa(venta: venta)

    @Transaction
    suspend fun crearVenta(venta:VentaRelationTransacciones){
        addvendedor(venta.vendedor)
        addCliente(venta.cliente)
        insertarTipoVenta(venta.tipo_venta)
        addventa(venta.venta)
    }
    @Transaction
    @Query("SELECT * FROM venta")
    suspend fun getAllventas(): List<VentaRelationTransacciones>


    //funciones detalle ventas
    @Transaction
    @Query("SELECT * FROM detalle_venta")
    suspend fun  getAllDetalleVenta(): List<DetalleVentaRelation>

    //funciones usuarios
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRollUsuario(roll: roll_usuarios)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRollUsuario(roll: usuario)

    @Transaction
    suspend fun crearUsuarios(usuariosRelation: UsuariosRelation){
        addRollUsuario(usuariosRelation.roll_usuario)
        addRollUsuario(usuariosRelation.usuarios)

    }
    @Transaction
    @Query("SELECT * FROM usuario")
    suspend fun getAllUsuarios(): List<UsuariosRelation>


}