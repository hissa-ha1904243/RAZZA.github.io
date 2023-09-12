package com.example.razzashoppingapp.room.entity

import androidx.room.*
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.Date

//@Entity(tableName = "order_table",
//    foreignKeys = [
//        ForeignKey(entity = User::class,
//            parentColumns = ["id"],
//            childColumns = ["userId"],
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE)
//    ])

//@TypeConverters
//
//@Serializable
//@Entity(tableName = "order_table")
data class Order (
    //@PrimaryKey(autoGenerate = true)
    @DocumentId
    var id: String ="",

    //@ColumnInfo(index = true)
    var userEmail: String = "",
    var total: Double = 0.0,
    var status:String, //processing,shipped,delivered
    @Contextual
    var date: String
){
    constructor(): this("","",0.0,"","")

//    constructor(userId: Int,
//                total: Double,
//                status: String,
//                date: String
//    ): this(id=0, userId, total, status, date)

}

