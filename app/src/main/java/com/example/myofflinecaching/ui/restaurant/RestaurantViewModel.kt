package com.example.myofflinecaching.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myofflinecaching.api.RestaurantApi
import com.example.myofflinecaching.data.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RestaurantViewModel
    @Inject
    constructor(
        api: RestaurantApi
    ): ViewModel() {

        private val restaurantLiveData = MutableLiveData<List<Restaurant>>()

        val restaurants : LiveData<List<Restaurant>> = restaurantLiveData
        init {
            viewModelScope.launch {
                val restaurants = api.getRestaurants()

                restaurantLiveData.value = restaurants
            }
        }

}