//package com.example.razzashoppingapp.room.dao
//
//import androidx.room.*
//import com.example.razzashoppingapp.room.entity.Category
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface CategoryDao {
//    @Query("SELECT COUNT(id) FROM category_table")
//    fun getCategoryCount():Int
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun addCategories(categories: List<Category>)
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun addCategory(category: Category)
//    @Query("SELECT * FROM category_table ORDER BY id ASC")
//    fun getAllCategories(): Flow<List<Category>>
//    @Query("SELECT * FROM category_table WHERE id = :categoryId")
//    fun getCategory(categoryId : String): Flow<Category>
//    @Delete
//    fun deleteCategory(category: Category)
//    @Update
//    fun updateCategory(category: Category)
//    @Query(value = "DELETE FROM category_table")
//    fun deleteAllCategories()
//}