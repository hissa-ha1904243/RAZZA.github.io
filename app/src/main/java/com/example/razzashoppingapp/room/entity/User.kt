package com.example.razzashoppingapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

//@Serializable
//@Entity(tableName = "user_table")
data class User (
        //@PrimaryKey(autoGenerate = true)
        @DocumentId
        var id: String = "",
        var username: String = "",
        var password: String = "",
        var email: String = "",
        var phone: String = ""
    ){
        constructor():this("", "", "", "", "")
//        constructor(
//                username: String,
//                password: String,
//                email: String,
//                phone: String):this(
//                id=0, username, password,email,phone
//                )


}

