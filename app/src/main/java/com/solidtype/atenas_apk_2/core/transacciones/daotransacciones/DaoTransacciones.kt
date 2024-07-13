package com.solidtype.atenas_apk_2.core.transacciones.daotransacciones

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
import com.solidtype.atenas_apk_2.perfil_administrador.domain.modelo.administrador
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.categoria
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import kotlinx.coroutines.flow.Flow

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

    //Funciones Inventario
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategorias(categoria: List<categoria>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersona(provedoor: persona)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonas(provedoor: List<persona>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInventario(inventario: inventario)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInventarios(inventario: List<inventario>)


    @Transaction
    suspend fun crearInventario(addInventario: InventarioModeloRelation){
        println("este es el dato que el inventario en crear inventario Dao -> $addInventario ")
        addCategia(addInventario.categoria)
        addPersona(addInventario.provedor)
        addInventario(addInventario.inventario)
        println("*****************************************************************************************")
        println("datos categoria -> ${addInventario.categoria}")
        println("datos persona -> ${addInventario.provedor}")
        println("datos inventario -> ${addInventario.inventario}")

        println("este es el dato que el inventario en crear inventario Dao ")
        println("***************************************************************************************** ")
    }

    @Transaction
    suspend fun crearInventarios(inventarios: List<InventarioModeloRelation>){

        println("datos inventarios en la funcio de transaccion -> $inventarios")
        val categoria:List<categoria> = inventarios.map{ it.categoria}
        val persona:List<persona> = inventarios.map{ it.provedor}
        val inventario:List<inventario> = inventarios.map{ it.inventario}
        println("datos categoria -> $categoria")
        println("datos persona -> $persona")
        println("datos inventario -> $inventario")
        addCategorias(categoria)
        addPersonas(persona)
        addInventarios(inventario)
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
    suspend fun crearUsuario(usuariosRelation: UsuariosRelation){
        addRollUsuario(usuariosRelation.roll_usuario)
        addRollUsuario(usuariosRelation.usuarios)
    }

    @Transaction
    @Query("SELECT * FROM usuario")
    suspend fun getAllUsuarios(): List<UsuariosRelation>

    //funciones administrador
    @Query("SELECT * FROM administrador")
    suspend fun getAdmins(): List<administrador>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAdministradore(admin : administrador)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOneAdmin(adminsitrador:administrador)

}