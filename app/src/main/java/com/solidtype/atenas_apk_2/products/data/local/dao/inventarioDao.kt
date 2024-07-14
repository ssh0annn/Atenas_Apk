package com.solidtype.atenas_apk_2.products.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import kotlinx.coroutines.flow.Flow

@Dao
interface inventarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInventario(inventario : inventario)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInventarios(inventario : List<inventario>)
    @Query("select * from inventario")
    fun getInventario(): Flow<List<inventario>>
    @Query("select * from inventario where id_categoria ==:id_cate")
    fun getInventariosByIdCategoria(id_cate :Int): Flow<List<inventario>>
    @Query("select * from inventario where id_proveedor ==:id_prov")
    fun getInventariosByIdProveedor(id_prov :Int): Flow<List<inventario>>
    @Query("select * from inventario where id_inventario ==:id")
    suspend fun getInventariosById(id :Int): inventario

    @Query("""SELECT * FROM inventario 
        WHERE id_categoria LIKE '%' || :any || '%'
        OR id_inventario LIKE '%' || :any || '%'
        OR id_proveedor LIKE '%' || :any || '%'
        OR nombre LIKE '%' || :any || '%'
        OR marca LIKE '%' || :any || '%'
        OR modelo LIKE '%' || :any || '%'
        OR cantidad LIKE '%' || :any || '%'
        OR precio_compra LIKE '%' || :any || '%'
        OR precio_venta LIKE '%' || :any || '%'
        OR descripcion LIKE '%' || :any || '%'
 
        """)
    fun buscarEnInventario(any:String):Flow<List<inventario>>

    @Update
    suspend fun updateInventario(inventario: inventario)
    @Update
    suspend fun updateInventario(inventario: List<inventario>)
    @Transaction
    suspend fun deleteInventario(inventario: inventario){
        inventario.estado = false
        updateInventario(inventario)
    }
    @Transaction
    suspend fun deleteInventario(inventario: List<inventario>){
        val inven:List<inventario> = inventario.map { inventario(
            id_inventario=it.id_inventario,
            id_categoria=it.id_categoria,
            id_proveedor=it.id_proveedor,
            nombre=it.nombre,
            marca=it.marca,
            modelo=it.modelo,
            cantidad=it.cantidad,
            precio_compra=it.precio_compra,
            precio_venta=it.precio_venta,
            impuesto=it.impuesto,
            descripcion=it.descripcion,
            estado=false
        ) }
        updateInventario(inven)
    }
}