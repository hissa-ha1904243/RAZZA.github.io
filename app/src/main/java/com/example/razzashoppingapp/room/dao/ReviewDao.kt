package com.example.razzashoppingapp.room.dao

import androidx.room.*
import com.example.razzashoppingapp.room.entity.Review
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT COUNT(id) FROM review_table")
    fun getReviewCount():Int
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addReview(review: Review)
    @Query("SELECT * FROM review_table ORDER BY id ASC")
    fun getAllReviews(): Flow<List<Review>>
    @Query("SELECT * FROM review_table WHERE id = :id")
    fun getReview(id : Int): Flow<Review>
    @Delete
    fun deleteReview(review: Review)
    @Update
    fun updateReview(review: Review)
    @Query(value = "DELETE FROM review_table")
    fun deleteAllReviews()
    @Query("SELECT * FROM review_table WHERE pid= :pid")
    fun getProductReviews(pid:Int):Flow<List<Review>>
}