package com.example.razzashoppingapp.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.razzashoppingapp.room.entity.Category
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable
//@Entity(tableName = "product_table",
//    foreignKeys = [
//        ForeignKey(entity = Category::class,
//            parentColumns = ["id"],
//            childColumns = ["category"],
//            onUpdate = ForeignKey.CASCADE,
//            onDelete = ForeignKey.CASCADE)
//    ])
//@Serializable
//@Entity(tableName = "product_table")
data class Product(
//    @PrimaryKey(autoGenerate = true)
    @DocumentId
    var id: String="",
    var title: String="",
    var price: Double=0.0,
    var description: String="",
    //@ColumnInfo(index = true)
    var category: String="",
    var image: String="",
    var rating: Double= 0.0
) {
    constructor(): this("", "",  0.0,  "", "", "", 0.0)

//    constructor(title: String,
//                price: Double,
//                description: String,
//                category: Int,
//                image: String
//    ): this(id=0, title, price, description, category, image, rating = 0.0)

}
