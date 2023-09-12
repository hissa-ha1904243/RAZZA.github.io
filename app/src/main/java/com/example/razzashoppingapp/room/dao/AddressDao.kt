package com.example.razzashoppingapp.room.dao

import androidx.room.*
import com.example.razzashoppingapp.room.entity.Address
import com.example.razzashoppingapp.room.entity.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {
    @Query("SELECT * FROM address_table ORDER BY id ASC")
    fun getAllAddresses(): Flow<List<Address>>
    @Query("SELECT COUNT(id) FROM address_table")
    fun getAddressCount():Int
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAddress(address: Address)
    @Query("SELECT * FROM address_table WHERE id = :addid")
    fun getAddress(addid : Int): Flow<Address>
    @Delete
    fun deleteAddress(address: Address)
    @Update
    fun updateAddress(address: Address)
    @Query(value = "DELETE FROM address_table")
    fun deleteAllAddress()
}