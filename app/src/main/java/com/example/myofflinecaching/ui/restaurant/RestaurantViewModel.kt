package com.example.myofflinecaching.ui.restaurant

import androidx.lifecycle.*
import com.example.myofflinecaching.api.RestaurantApi
import com.example.myofflinecaching.data.Restaurant
import com.example.myofflinecaching.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RestaurantViewModel
    @Inject
    constructor(
        repository: RestaurantRepository
    ): ViewModel() {

        val restaurant = repository.getRestaurant().asLiveData()



//        private val restaurantLiveData = MutableLiveData<List<Restaurant>>()
//
//        val restaurants : LiveData<List<Restaurant>> = restaurantLiveData
//        init {
//            viewModelScope.launch {
//                val restaurants = api.getRestaurants()
//
//                restaurantLiveData.value = restaurants
//            }
//        }


}