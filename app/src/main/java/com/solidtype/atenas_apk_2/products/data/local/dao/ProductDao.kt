package com.solidtype.atenas_apk_2.products.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product_table")
    fun getProductss(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPro(pro: ProductEntity)
    @Query("select * from Product_Table WHERE Code_Product = :code ")
    fun getAllProductById(code: Int): Flow<List<ProductEntity>>

    @Query(
        "SELECT * FROM Product_Table WHERE " +
                "Code_Product LIKE :code " +
                "OR Category_Product LIKE :code " +
                "OR Count_Product LIKE :code " +
                "OR Description_Product LIKE :code " +
                "OR Model_Product LIKE :code " +
                "OR Name_Product LIKE :code || '%' " +  // Nombre que comienza con las letras especificadas
                "OR Price_Product LIKE :code " +
                "OR Price_Vending_Product LIKE :code " +
                "OR Tracemark_Product LIKE :code "
    )
    fun getProductsLike(code: String): Flow<List<ProductEntity>>

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateProduct(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(product: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: ProductEntity)

    @Delete
    fun deleteProduct(codigo: ProductEntity)

}