package com.example.razzashoppingapp.room.entity

import com.google.firebase.firestore.DocumentId

//@Entity(tableName = "wishlist_table",
//    foreignKeys = [
//        ForeignKey(entity = User::class,
//            parentColumns = ["id"],
//            childColumns = ["userId"],
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE),
//        ForeignKey(entity = Product::class,
//            parentColumns = ["id"],
//            childColumns = ["pid"],
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE)
//    ])
//@Entity(tableName = "wishlist_table")
data class WishListItem(
    //@PrimaryKey(autoGenerate = true)
    @DocumentId
    var id: String = "",
    //@ColumnInfo(index = true)
    var userEmail: String="",
    //@ColumnInfo(index = true)
    var pid: String=""
){
    constructor() : this(id="", userEmail="",pid="")

}

