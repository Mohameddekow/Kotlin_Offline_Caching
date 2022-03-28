package com.example.myofflinecaching.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myofflinecaching.dao.RestaurantDao


@Database(entities = [Restaurant::class], version = 1)
abstract class RestaurantDatabase:  RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
}