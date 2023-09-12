package com.example.razzashoppingapp.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

//@Entity(tableName = "review_table",
//
//        foreignKeys = [
//        androidx.room.ForeignKey(
//            entity = Product::class,
//            parentColumns = ["id"],
//            childColumns = ["pid"],
//            onUpdate = androidx.room.ForeignKey.CASCADE,
//            onDelete = androidx.room.ForeignKey.CASCADE
//        ),
//            androidx.room.ForeignKey(
//            entity = User::class,
//            parentColumns = ["id"],
//            childColumns = ["uid"],
//            onUpdate = androidx.room.ForeignKey.CASCADE,
//            onDelete = androidx.room.ForeignKey.CASCADE
//        )
//])
//@Serializable
//@Entity(tableName = "review_table")
data class Review(
    //@PrimaryKey(autoGenerate = true)
    @DocumentId
    var id : String = "",
    //@ColumnInfo(index = true)
    var pid: String = "",
    //@ColumnInfo(index = true)
    var uEmail: String ="",
    var text: String = "",
    var rating: Int = 0
){
    constructor():this("","","", "", 0)
//    constructor(text:String,rating:Int,pid:Int,uid:Int
//    ): this(id=0, pid, uid, text, rating)
}
