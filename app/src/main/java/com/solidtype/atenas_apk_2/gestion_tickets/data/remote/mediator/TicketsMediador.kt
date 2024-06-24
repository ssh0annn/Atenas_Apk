package com.solidtype.atenas_apk_2.gestion_tickets.data.remote.mediator

import com.solidtype.atenas_apk_2.core.Transacciones.DaoTransacciones.DaoTransacciones
import com.solidtype.atenas_apk_2.core.remote.dataCloud.DataCloud
import com.solidtype.atenas_apk_2.gestion_tickets.data.remote.DBtickets.DbTicketsImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TicketsMediador @Inject constructor (
    private val dbTickets: DbTicketsImpl,
    private val  daoTransacciones: DaoTransacciones,
    private val database : DataCloud
) {



    suspend fun getDatabase(){
        withContext(Dispatchers.IO) {
            val dataCon = dbTickets.entityToString(daoTransacciones.getAllTickets())
            println(dataCon)
            dataCon.forEachIndexed { index, ticketRelation ->
                println("TicketModeloRelation ${index + 1}:")
                println(ticketRelation)
                println()
            }

            database.insertAllToCloud2("ticket",dataCon,"transationTicket")
        }
    }

}