package com.example.myofflinecaching.ui.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myofflinecaching.data.Restaurant
import com.example.myofflinecaching.databinding.RowLayoutBinding
import com.example.myofflinecaching.ui.loadImageWithGlide

class RestaurantAdapter(
    private val clickListener: ItemClickListener
): RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>(){
    private var restaurantList = emptyList<Restaurant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding: RowLayoutBinding = RowLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentRestaurantItem = restaurantList[position]
        holder.onBind(currentRestaurantItem, clickListener)
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }



    inner class RestaurantViewHolder(
        private val binding: RowLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun onBind(restaurantItem: Restaurant, action: ItemClickListener){
            binding.apply {

                resName.text = restaurantItem.name
                resType.text = restaurantItem.type
                resAddr.text = restaurantItem.address

                resLogo.loadImageWithGlide(restaurantItem.logo)


            }

            binding.root.setOnClickListener {
                action.onItemClicked(it, restaurantItem, adapterPosition)
            }
        }

    }

    fun setData(items: List<Restaurant> ){
        restaurantList = items
//        RestaurantComparator()
        notifyDataSetChanged()
    }

    interface ItemClickListener{
        fun onItemClicked(view: View, restaurantListItem: Restaurant, position: Int)
    }



    //comparison
    class RestaurantComparator(): DiffUtil.ItemCallback<Restaurant>(){
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) =
            oldItem == newItem

    }
}
