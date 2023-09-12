package com.example.razzashoppingapp.room.dao

import androidx.room.*
import com.example.razzashoppingapp.room.entity.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT COUNT(id) FROM item_table")
    fun getItemCount():Int
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItems(items: List<Item>)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(item: Item)
    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllItems(): Flow<List<Item>>
    @Query("SELECT * FROM item_table WHERE id = :itemId")
    fun getItem(itemId : Int): Flow<Item>
    @Query("SELECT * FROM item_table WHERE cartId = :cartId")
    fun getAllCartItems(cartId : Int): Flow<Item>
    @Delete
    fun deleteItem(item: Item)
    @Update
    fun updateItem(item: Item)
    @Query(value = "DELETE FROM item_table")
    fun deleteAllItems()
    @Query("SELECT * FROM item_table WHERE userId= :uid")
    fun getUserCartItems(uid:Int):Flow<List<Item>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCartItem(item: Item)
    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllCartItems(): Flow<List<Item>>
    @Delete
    fun deleteCartItem(item: Item)
}

