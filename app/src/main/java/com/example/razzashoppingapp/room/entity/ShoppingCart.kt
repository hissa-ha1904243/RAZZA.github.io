package com.example.razzashoppingapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

//@Serializable
//@Entity(tableName = "cart_table")
data class ShoppingCart (
    //@PrimaryKey(autoGenerate = true)
    @DocumentId
    var id: String = "",

    //@ColumnInfo(index = true)
    var userId: String = "",
    var total: Double = 0.0,
){
    constructor():this("", "", 0.0)
//    constructor(userId: Int,
//                total: Double
//    ): this(id=0, userId, total)
}