package com.macv.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
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

@BindingAdapter("android:src")
fun setImageViewResource(view: ImageView, resId : Int) {
    view.setImageResource(resId)
}