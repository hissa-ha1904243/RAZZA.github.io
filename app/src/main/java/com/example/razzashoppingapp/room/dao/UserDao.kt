package com.example.razzashoppingapp.room.dao

import androidx.room.*
import com.example.razzashoppingapp.room.entity.Product
import com.example.razzashoppingapp.room.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): Flow<List<User>>
    @Query("SELECT COUNT(id) FROM user_table")
    fun getUserCount():Int
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)
    @Query("SELECT * FROM user_table WHERE id = :userid")
    fun getUser(userid : Int): User
    @Delete
    fun deleteUser(user: User)
    @Update
    fun updateUser(user: User)
}