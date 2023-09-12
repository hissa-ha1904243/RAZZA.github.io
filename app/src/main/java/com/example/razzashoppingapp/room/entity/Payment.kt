package com.example.razzashoppingapp.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId


//@Entity(tableName = "payment_table",
//    foreignKeys = [
//        ForeignKey(entity = User::class,
//            parentColumns = ["id"],
//            childColumns = ["userID"],
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE),
//        ForeignKey(entity = Order::class,
//            parentColumns = ["id"],
//            childColumns = ["orderID"],
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE)
//    ])
data class Payment (
    //@PrimaryKey(autoGenerate = true)
    @DocumentId
    var id: String = "",
    //@ColumnInfo(index = true)
    var userID: String = "",
    //@ColumnInfo(index = true)
    var orderID: String="",
    var amount: Double = 0.0

){
    constructor():this("","","", 0.0)
}