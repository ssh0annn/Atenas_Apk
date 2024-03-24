package com.solidtype.atenas_apk_2.products.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.solidtype.atenas_apk_2.products.domain.model.ProductEntity
import com.solidtype.atenas_apk_2.products.domain.model.DataProductos
import com.solidtype.atenas_apk_2.products.domain.userCases.getProductosByCodigo
import kotlinx.coroutines.flow.Flow
import java.util.Objects

@Dao
interface ProductDao{
    @Query("SELECT * FROM product_table")
    fun getProducts(): Flow<List<ProductEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPro(pro: ProductEntity)



    @Query("select * from Product_Table WHERE Code_Product = :code ")
    fun getAllProductById(code: Int): Flow<List<ProductEntity>>

    @Query("select * from Product_Table where Code_Product like :code or Category_Product like :code " +
            "or Count_Product like :code or Description_Product like :code " +
            "or Model_Product like :code or Name_Product like :code " +
            "or Price_Product like :code or Price_Vending_Product like :code " +
            "or Tracemark_Product like :code ")
    fun getProductsLike(code: String): Flow<List<ProductEntity>>

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateProduct(product : ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(product:List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: ProductEntity)

    @Delete
    fun deleteProduct(codigo: ProductEntity)

}