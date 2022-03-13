package com.example.myofflinecaching.difUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.myofflinecaching.data.Restaurant

class MyDiffUtil(
    private val oldRestaurant: List<Restaurant>,
    private val newRestaurant: List<Restaurant>

): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldRestaurant.size
    }

    override fun getNewListSize(): Int {
        return newRestaurant.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRestaurant[oldItemPosition].name == newRestaurant[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldRestaurant[oldItemPosition].name != newRestaurant[newItemPosition].name ->{
                false
            }
            oldRestaurant[oldItemPosition].type != newRestaurant[newItemPosition].type ->{
                false
            }
            oldRestaurant[oldItemPosition].address != newRestaurant[newItemPosition].address ->{
                false
            }
            else -> {
                true
            }
        }
    }
}