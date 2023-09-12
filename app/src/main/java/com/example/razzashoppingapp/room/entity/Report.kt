package com.example.razzashoppingapp.room.entity

import com.google.firebase.firestore.DocumentId


data class Report(
    @DocumentId
    var id: String,
    var userID: Int,
    var zoneNo: Int,
    var streetNo: Int,
    var buildingNo: Int,
){

}
