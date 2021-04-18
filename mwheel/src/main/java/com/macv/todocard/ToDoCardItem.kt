    package com.macv.todocard

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.macv.mwheel.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ToDoCardItem(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("emoji") val emoji: String = "",
        @SerializedName("iconBackgroundColor") val iconBackgroundColor: Int = R.color.category_image_bg,
        @SerializedName("name") val name: String = "",
        @SerializedName("points") val points: Int = 0
) : Parcelable