package com.example.razzashoppingapp.json_classes

import kotlinx.serialization.Serializable

@Serializable
data class AddressModel (
    val id: Int,
    val userID: Int,
    val zoneNo: Int,
    val streetNo: Int,
    val buildingNo: Int,
        )