package com.example.myofflinecaching.data

import androidx.room.withTransaction
import com.example.myofflinecaching.api.RestaurantApi
import com.example.myofflinecaching.util.networkBoundResource
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi,
    private val db: RestaurantDatabase
) {

    private val restaurantDao = db.restaurantDao()

    fun getRestaurant() = networkBoundResource(
        query = {
            restaurantDao.getAllRestaurant()
        },
        fetch = {
            api.getRestaurants()
        },
        saveFetchResult = { restaurant ->
            db.withTransaction {
                restaurantDao.deleteAllRestaurant()
                restaurantDao.insertRestaurant(restaurant)
            }
        }
    )

}