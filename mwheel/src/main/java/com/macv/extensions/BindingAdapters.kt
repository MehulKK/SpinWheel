package com.macv.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.macv.category.CategoryComponent
import com.macv.category.CategoryItem

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: CategoryComponent?, items: ArrayList<CategoryItem>?) {
    recyclerView?.let {
        items?.let { it1 -> it.setDynamicList(it1) }
    }
    /*if (recyclerView.adapter is UserAdapter) {
        (recyclerView.adapter as UserAdapter).setData(items)
    }*/
}