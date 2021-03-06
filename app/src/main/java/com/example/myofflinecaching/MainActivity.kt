package com.example.myofflinecaching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myofflinecaching.data.Restaurant
import com.example.myofflinecaching.databinding.ActivityMainBinding
import com.example.myofflinecaching.ui.restaurant.RestaurantAdapter
import com.example.myofflinecaching.ui.restaurant.RestaurantViewModel
import com.example.myofflinecaching.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RestaurantAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


//        //set up the toolbar
        val toolBar = binding.mainActivityToolbar
        setSupportActionBar(toolBar)


        //adapter
        val myAdapter = RestaurantAdapter(this)

        //set data on recyclerView
//        val homeData = GlobalData.HomeData
//        myAdapter.setData(homeData)

        binding.apply {
            restaurantRecyclerview.apply {
                adapter = myAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            viewModel.restaurant.observe(this@MainActivity, Observer { result ->
                result.data?.let { myAdapter.setData(it) }

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                errorMessage.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                errorMessage.text = result.error?.localizedMessage

                println(result.toString())
            })
        }






//
//
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//

    }

    override fun onItemClicked(view: View, restaurantListItem: Restaurant, position: Int) {

    }
}