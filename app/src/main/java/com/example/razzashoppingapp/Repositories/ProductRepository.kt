package com.example.razzashoppingapp.Repositories

import android.content.Context
import com.example.razzashoppingapp.json_classes.ProductModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ProductRepository {
    var prodcuts = listOf<ProductModel>()
    fun getProducts(context: Context): List<ProductModel> {
        if (prodcuts.isEmpty()) {
            val productJson = context.assets
                .open("products.json")
                .bufferedReader()
                .use{
                    it.readText() }
            prodcuts = Json.decodeFromString(productJson)
        }
        return prodcuts
    }
}