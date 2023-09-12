package com.example.razzashoppingapp.room.dao

import androidx.room.*
import com.example.razzashoppingapp.room.entity.Order
import com.example.razzashoppingapp.room.entity.ShoppingCart
import kotlinx.coroutines.flow.Flow
@Dao
interface ShoppingCartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCart(cart: ShoppingCart)
    @Query("SELECT * FROM cart_table WHERE id = :id")
    fun getCart(id : Int): Flow<ShoppingCart>
    @Query("SELECT * FROM cart_table")
    fun getAllCarts(): Flow<List<ShoppingCart>>
    @Delete
    fun deleteCart(cart: ShoppingCart)
    @Query(value = "DELETE FROM cart_table")
    fun deleteAllCarts()
    @Update
    fun updateCart(cart: ShoppingCart)
    @Query("SELECT * FROM cart_table WHERE userId = :uid")
    fun getUserCart(uid:Int): Flow<List<ShoppingCart>>
}