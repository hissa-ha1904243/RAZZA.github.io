package com.example.razzashoppingapp.room.dao

import androidx.room.*
import com.example.razzashoppingapp.room.entity.Payment
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPayment(payment: Payment)
    @Query("SELECT * FROM payment_table ORDER BY id ASC")
    fun getAllPayments(): Flow<List<Payment>>
    @Query("SELECT * FROM payment_table WHERE id = :id")
    fun getPayment(id : Int): Flow<Payment>
    @Update
    fun updatePayment(payment: Payment)
    @Delete
    fun deletePayment(payment: Payment)
    @Query(value = "DELETE FROM payment_table")
    fun deleteAllPayments()
}


