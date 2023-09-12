package com.example.razzashoppingapp.Repositories

import android.content.Context
import com.example.razzashoppingapp.json_classes.OrderModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class OrderRepository {
    var orderModels = listOf<OrderModel>()
    fun getOrders(context: Context): List<OrderModel> {
        if (orderModels.isEmpty()) {
            val orderJson = context.assets
                .open("orders.json")
                .bufferedReader()
                .use{
                    it.readText() }
            orderModels = Json.decodeFromString(orderJson)
        }
        return orderModels
    }
}