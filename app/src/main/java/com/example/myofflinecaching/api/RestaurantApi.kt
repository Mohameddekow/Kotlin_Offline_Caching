package com.example.myofflinecaching.api

import com.example.myofflinecaching.data.Restaurant
import retrofit2.http.GET

interface RestaurantApi {

    @GET("restaurant/random_restaurant?size=50")
    suspend fun getRestaurants(): List<Restaurant>

}