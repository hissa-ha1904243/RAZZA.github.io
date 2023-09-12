package com.example.razzashoppingapp.json_classes

import kotlinx.serialization.Serializable

@Serializable
data class RatingModel(
    val rate: Double,
    val count: Int)
