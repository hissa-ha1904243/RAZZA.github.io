package com.example.razzashoppingapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

//@Serializable
//@Entity(tableName = "category_table")
data class Category(
//    @PrimaryKey (autoGenerate = true)
    @DocumentId
    var id: String="",
    var name: String="",
) {
    constructor(): this("","")

//    constructor(name: String
//    ):this(id= "", name)

}
