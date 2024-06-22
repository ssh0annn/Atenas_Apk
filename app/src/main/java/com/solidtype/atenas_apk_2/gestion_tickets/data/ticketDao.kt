package com.solidtype.atenas_apk_2.gestion_tickets.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.solidtype.atenas_apk_2.core.entidades.tipo_venta
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.TicketwithRelation
import com.solidtype.atenas_apk_2.gestion_tickets.domain.model.ticket
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface ticketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addTicket(ticket : ticket)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addTickets(ticket : List<ticket>)
    @Query("select * from ticket WHERE estado == true")
    fun getTickets(): Flow<List<ticket>>
    @Query("select * from ticket WHERE estado == false")
    fun getTicketsEliminados(): Flow<List<ticket>>
    @Query("select * from ticket")
    fun getTicketsTodos(): Flow<List<ticket>>
    @Query("SELECT * FROM ticket WHERE fecha_inicio BETWEEN :fechaIni AND :fechaFin")
    fun getTicketsByFechas(fechaIni: LocalDate, fechaFin:LocalDate):Flow<List<ticket>>
    @Query("select * from ticket where id_vendedor ==:id")
    fun getTicketsByIdUsuario(id :Long): Flow<List<ticket>>
    @Query("select * from ticket where id_cliente ==:id")
    fun getTicketsByIdPersona(id :Long): Flow<List<ticket>>
    @Query("select * from ticket where id_tipo_venta ==:id")
    fun getTicketsByIdTipoVenta(id :Long): Flow<List<ticket>>
    @Query("select * from ticket where id_dispositivo ==:id")
    fun getTicketsByIdDispositivo(id :Long): Flow<List<ticket>>
    @Query("select * from ticket where id_ticket ==:id")
    suspend fun getTicketById(id :Long): ticket
    @Update
    suspend fun updateTicket(ticket: ticket)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarTipoVenta(tipoVenta: tipo_venta)
    @Transaction
   suspend fun deleteTicket(ticket: ticket){
        ticket.estado = false
        updateTicket(ticket)
    }
    @Transaction
    suspend fun crearTicket(ticketTransaction: TicketwithRelation){
        insertarTipoVenta(ticketTransaction.tipo_venta)
        addTicket(ticketTransaction.ticket)
    }

    @Transaction
    @Query("SELECT * FROM ticket")
    fun getTicketswithRelation() : Flow<List<TicketwithRelation>>

    @Transaction
    @Query("""SELECT * FROM ticket WHERE id_ticket LIKE '%'|| :any || '%'  
           OR id_servicio LIKE '%'|| :any || '%'
           OR id_cliente LIKE '%'|| :any || '%'
           OR nota LIKE '%'|| :any || '%'
           OR imei LIKE '%'|| :any || '%'
           OR descripcion LIKE '%'|| :any || '%'
    """)
    fun buscarTicketsWithRelation(any:String): Flow<List<TicketwithRelation>>
}