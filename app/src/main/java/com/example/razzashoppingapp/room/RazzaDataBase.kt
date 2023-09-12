package com.example.razzashoppingapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.razzashoppingapp.room.dao.*
import com.example.razzashoppingapp.room.entity.*

//
//@Database(entities = [Address::class ,
//    Category::class, Item::class, Order::class, Product::class, Review::class, User::class,
//    WishListItem::class, Payment::class, OrderItem::class, ShoppingCart::class ], version = 28, exportSchema = false)
abstract class RazzaDataBase: RoomDatabase() {
//    abstract fun AddressDao() : AddressDao
//    abstract fun CategoryDao() : CategoryDao
//    abstract fun ItemDao() : ItemDao
//    abstract fun OrderDao() : OrderDao
//    abstract fun ProductDao() : ProductDao
//    abstract fun PaymentDao() : PaymentDao
//    abstract fun ReviewDao() : ReviewDao
//    abstract fun UserDao() : UserDao
//    abstract fun WishListDao() : WishListDao
//    abstract fun OrderItemDao() : OrderItemDao
//    abstract fun ShoppingCartDao() : ShoppingCartDao
//
//
//    companion object {
//        private var database: RazzaDataBase? = null
//
//        fun getDatabase(context: Context): RazzaDataBase {
//            if (database == null) {
//                database = Room.databaseBuilder(
//                    context.applicationContext,
//                    RazzaDataBase::class.java,
//                "razza_db"
//                ).fallbackToDestructiveMigration().build()
//            }
//            return database as RazzaDataBase
//        }
//    }
}