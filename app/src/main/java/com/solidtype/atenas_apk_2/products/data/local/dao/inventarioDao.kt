package com.solidtype.atenas_apk_2.products.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.products.domain.model.actualizacion.inventario
import kotlinx.coroutines.flow.Flow

@Dao
interface inventarioDao {
    @Insert
    suspend fun addInventario(inventario : inventario)
    @Insert
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
    @Delete
    suspend fun deleteInventario(inventario: inventario)
}