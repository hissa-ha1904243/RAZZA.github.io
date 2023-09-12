package com.example.razzashoppingapp.json_classes

import kotlinx.serialization.Serializable

@Serializable
data class OrderModel (
    val id: Int,
    val total: Int,
    var processing:Boolean,
    var shipped:Boolean,
    var delivered:Boolean
)