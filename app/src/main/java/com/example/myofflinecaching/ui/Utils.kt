package com.example.myofflinecaching.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myofflinecaching.R

fun ImageView.loadImageWithGlide(url: String){
    val option = RequestOptions().placeholder(R.drawable.profile)
        .error(R.drawable.profile)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}