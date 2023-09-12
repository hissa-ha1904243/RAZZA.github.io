package com.example.razzashoppingapp.json_classes

import kotlinx.serialization.Serializable

@Serializable
data class ReviewModel (
    val id: Int,
    val username: String,
    val rating: Double,
    val review: String
)