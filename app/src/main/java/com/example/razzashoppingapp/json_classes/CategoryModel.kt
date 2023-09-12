package com.example.razzashoppingapp.json_classes

import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel (
    val id:Int,
    val name: String
)