package com.example.myofflinecaching.di

import android.app.Application
import androidx.room.Room
import com.example.myofflinecaching.api.RestaurantApi
import com.example.myofflinecaching.constant.Constant.Companion.BASE_URL
import com.example.myofflinecaching.data.Restaurant
import com.example.myofflinecaching.data.RestaurantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideRestaurant(retrofit: Retrofit): RestaurantApi =
        retrofit.create(RestaurantApi::class.java)


    @Provides
    @Singleton
    fun provideDatabase(app: Application): RestaurantDatabase  =
        Room.databaseBuilder(app, RestaurantDatabase::class.java, "restaurant_database")
            .build()
}