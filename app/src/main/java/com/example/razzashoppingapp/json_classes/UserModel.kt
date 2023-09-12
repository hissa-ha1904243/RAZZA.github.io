package com.example.razzashoppingapp.json_classes

import kotlinx.serialization.Serializable

@Serializable
data class UserModel (
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val phone: Int
        )