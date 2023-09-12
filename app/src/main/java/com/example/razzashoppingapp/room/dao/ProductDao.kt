//package com.example.razzashoppingapp.room.dao
//
//import androidx.room.*
//import com.example.razzashoppingapp.room.entity.Product
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface ProductDao {
//    @Query("SELECT COUNT(id) FROM product_table")
//    fun getProductCount():Int
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun addProducts(products: List<Product>)
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun addProduct(product: Product)
//    @Query("SELECT * FROM product_table ORDER BY id ASC")
//    fun getAllProducts(): Flow<List<Product>>
//    @Query("SELECT * FROM product_table WHERE id = :id")
//    fun getProductsByCategory(id: Int): Flow<List<Product>>
//    @Query("SELECT * FROM product_table WHERE id = :id")
//    fun getProduct(id : Int): Flow<Product>
//    @Query("SELECT * FROM product_table WHERE title = :name")
//    fun getProducts(name : String): Flow<Product>
//    @Update
//    fun updateProduct(product: Product)
//    @Delete
//    fun deleteProduct(product: Product)
//    @Query(value = "DELETE FROM product_table")
//    fun deleteAllProducts()
//
//    @Query("SELECT * FROM product_table ORDER BY price;")
//    fun orderProductAsc(): Flow<List<Product>>
//
//    @Query("SELECT * FROM product_table ORDER BY price DESC;")
//    fun orderProductDes(): Flow<List<Product>>
//
//}
//
//
