package com.example.razzashoppingapp.room.dao

import androidx.room.*
import com.example.razzashoppingapp.room.entity.Order
import com.example.razzashoppingapp.room.entity.OrderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderItemDao {
    @Query("SELECT COUNT(id) FROM orderItem_table")
    fun getOrderItemCount():Int
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addOrder(order: OrderItem)
    @Query("SELECT * FROM orderItem_table")
    fun getAllOrderedItems(): Flow<List<OrderItem>>

}