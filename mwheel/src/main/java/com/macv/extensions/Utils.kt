package com.macv.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.macv.mwheel.R

@BindingAdapter("app:imageUrl", "app:placeHolder", "app:error")
fun loadImage(imageView: ImageView, url: String, holderDrawable: Drawable, errorDrawable: Drawable) {
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(12))

    Glide.with(imageView.context)
        .load(url)
        //.centerCrop()
        .apply(requestOptions)
        .into(imageView)

    //Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_category_image_placeholder)
        requestOptions.error(R.drawable.ic_category_image_placeholder)
        if (it.isNotEmpty()) {
            Glide.with(view.context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(it)
                    .into(view)
        } else {
            Glide.with(view.context)
                    .setDefaultRequestOptions(requestOptions)
                    .load("")
                    .into(view)
        }
    }
}

fun String.getPercentage() : String {
    if (this.isNotEmpty()){
        return String.format("%s%%",(this.toDouble() * 100))
    }
    return ""
}