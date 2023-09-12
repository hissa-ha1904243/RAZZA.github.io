package com.example.razzashoppingapp.json_classes
import kotlinx.serialization.Serializable

@Serializable
data class ProductModel(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: Int,
    val image: String,
    val rating: Double
    )