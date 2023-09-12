package com.example.razzashoppingapp.room.dao

import androidx.room.*
import com.example.razzashoppingapp.room.entity.Order
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {
    @Query("SELECT COUNT(id) FROM order_table")
    fun getOrderCount():Int
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addOrder(order: Order)
    @Query("SELECT * FROM order_table WHERE id = :orderId")
    fun getOrder(orderId : Int): Flow<Order>
    @Query("SELECT * FROM order_table")
    fun getAllOrders(): Flow<List<Order>>
    @Delete
    fun deleteOrder(order: Order)
    @Query(value = "DELETE FROM order_table")
    fun deleteAllOrder()
    @Update
    fun updateOrder(order: Order)
    @Query("SELECT * FROM order_table WHERE status ='processing'")
    fun getProcessingOrders(): Flow<List<Order>>
    @Query("SELECT * FROM order_table WHERE status ='shipped'")
    fun getShippedOrders(): Flow<List<Order>>
    @Query("SELECT * FROM order_table WHERE status ='delivered'")
    fun getDeliveredOrders():Flow<List<Order>>
    @Query("SELECT * FROM order_table WHERE userId = :uid")
    fun getUserOrders(uid:Int):Flow<List<Order>>
}
