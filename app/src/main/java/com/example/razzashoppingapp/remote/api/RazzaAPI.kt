package com.example.razzashoppingapp.remote.api

import com.example.razzashoppingapp.room.entity.*
import retrofit2.http.Body
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.Path

interface RazzaAPI {

    @GET("categories")
    suspend fun getCategories() : List<Category>
    @GET("products")
    suspend fun getProducts() : List<Product>
    @GET("addresses")
    suspend fun getAddresses() : List<Address>
    @GET("users")
    suspend fun getUsers() : List<User>
    @GET("orders")
    suspend fun getOrders() : List<Order>
    @GET("ordereditems")
    suspend fun getOrderedItems() : List<OrderItem>
    @GET("reviews")
    suspend fun getReviews() : List<Review>

//    @GET("/categories/{id}")
//    fun getCategory(@Path("id")id:Int) : List<Category>
//
//    @GET("/products")
//    fun getProducts() : List<Product>
//
//    @GET("/reviews")
//    fun getReviews() : List<Review>
//
//    @GET("/orders")
//    fun getOrders() : List<Order>
//
//    @GET("/addresses")
//    fun getAddresses() : List<Address>

}