package com.example.razzashoppingapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable


//@Serializable
//@Entity(tableName = "orderItem_table")
data class OrderItem(
    //@PrimaryKey
    @DocumentId
    var id:String="",
    var orderId:String="",
    var pid:String=""
) {
    constructor():this("","","")
}