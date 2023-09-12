package com.example.razzashoppingapp.room.dao

import androidx.room.*
import com.example.razzashoppingapp.room.entity.WishListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WishListDao {
    @Query("SELECT COUNT(id) FROM wishlist_table")
    fun getWishlistCount():Int
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWishlistItems(wishListItem: List<WishListItem>)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWishlistItem(wishListItem: WishListItem)
    @Query("SELECT * FROM wishlist_table ORDER BY id ASC")
    fun getAllWishlistItems(): Flow<List<WishListItem>>
    @Query("SELECT * FROM wishlist_table WHERE id = :id")
    fun getWishlistItem(id : Int): Flow<WishListItem>
    @Delete
    fun deleteWishlistItem(wishListItem: WishListItem)
    @Query(value = "DELETE FROM wishlist_table")
    fun deleteAllWishlistItems()
}