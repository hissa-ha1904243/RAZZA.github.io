package com.example.razzashoppingapp.Repositories

import android.content.Context
import com.example.razzashoppingapp.json_classes.ReviewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ReviewRepository {
    var reviews = listOf<ReviewModel>()
    fun getReviews(context: Context): List<ReviewModel> {
        if (reviews.isEmpty()) {
            val reviewJson = context.assets
                .open("reviews.json")
                .bufferedReader()
                .use{
                    it.readText() }
            reviews = Json.decodeFromString(reviewJson)
        }
        return reviews
    }
}