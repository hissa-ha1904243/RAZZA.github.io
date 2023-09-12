package com.example.razzashoppingapp.Repositories

import android.content.Context
import com.example.razzashoppingapp.json_classes.CategoryModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CategoryRepository {
    var categories = listOf<CategoryModel>()
    fun getCategories(context: Context): List<CategoryModel> {
        if (categories.isEmpty()) {
            val categoryJson = context.assets
                .open("categories.json")
                .bufferedReader()
                .use{
                    it.readText() }
            categories = Json.decodeFromString(categoryJson)
        }
        return categories
    }
}