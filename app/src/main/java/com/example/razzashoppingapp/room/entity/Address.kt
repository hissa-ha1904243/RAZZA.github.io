package com.example.razzashoppingapp.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

//@Entity(
//    tableName = "address_table",
//    foreignKeys = [
//        ForeignKey(
//            entity = User::class,
//            parentColumns = ["id"],
//            childColumns = ["userID"],
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//@Serializable
//@Entity(tableName = "address_table")
data class Address(
   // @PrimaryKey(autoGenerate = true)
    @DocumentId
    var id: String = "",
    var name: String,
    var city: String,
   // @ColumnInfo(index = true)
    var userEmail: String = "",
    var zoneNo: Int = 0,
    var streetNo: Int = 0,
    var buildingNo: Int = 0
){
    constructor(): this("", "", "", "", 0, 0, 0)

//    constructor(name:String,
//                city:String,
//                userID: Int,
//                zoneNo:Int,
//                streetNo:Int,
//                buildingNo:Int
//    ): this(id=0, name, city, userID, zoneNo, streetNo, buildingNo)


}
