package com.macv.extensions

import android.content.Context
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
        return String.format("%s%%", (this.toDouble() * 100))
    }
    return ""
}

fun dp2px(context: Context, dpValue: Float): Float {
    if (dpValue <= 0) return 0f
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f)
}

fun sp2px(context: Context, spValue: Float): Float {
    if (spValue <= 0) return 0f
    val scale = context.resources.displayMetrics.scaledDensity
    return spValue * scale
}

fun formatNum(time: Int): String {
    return if (time < 10) "0$time" else time.toString()
}

fun px2sp(context: Context, pxValue : Float) : Float = pxValue / context.resources.displayMetrics.scaledDensity

fun formatMillisecond(millisecond: Int): String? {
    val retMillisecondStr: String
    retMillisecondStr = if (millisecond > 99) {
        (millisecond / 10).toString()
    } else if (millisecond <= 9) {
        "0$millisecond"
    } else {
        millisecond.toString()
    }
    return retMillisecondStr
}