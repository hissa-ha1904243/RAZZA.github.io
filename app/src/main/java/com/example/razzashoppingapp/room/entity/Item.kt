package com.example.razzashoppingapp.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId

//@Entity(tableName = "item_table",
//    foreignKeys = [
//        ForeignKey(entity = Product::class,
//            parentColumns = ["id"],
//            childColumns = ["productId"],
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE),
//        ForeignKey(entity = ShoppingCart::class,
//            parentColumns = ["id"],
//            childColumns = ["cartId"],
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE)
//    ])
//@Entity(tableName = "item_table")
data class Item (
    //@PrimaryKey(autoGenerate = true)
    @DocumentId
    var id: String= "",
    var userEmail: String="",
    //@ColumnInfo(index = true)
    var productId:String = "",
    var subtotal: Double=0.0,
    var quantity: Int=0,
    //@ColumnInfo(index = true)
    var cartId:Int = 0
    ){
    constructor(): this("","","",0.0,0,0)

//    constructor(userEmail:String, productId:Int,subtotal: Double,quantity: Int): this(id=0, userEmail,productId,subtotal, quantity,cartId=0)


}
