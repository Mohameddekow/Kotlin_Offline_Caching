package com.example.myofflinecaching.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myofflinecaching.data.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: List<Restaurant>)


    @Query("DELETE FROM restaurants")
    suspend fun deleteAllRestaurant()


    @Query("SELECT * FROM restaurants")
    fun getAllRestaurant(): Flow<List<Restaurant>>
}