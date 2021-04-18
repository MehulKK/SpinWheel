    package com.macv.pointsitemcard

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.macv.mwheel.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PointsItem(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("image") val image: Int = 0,
        @SerializedName("title") val title: String = "",
        @SerializedName("description") val description: String = "",
        @SerializedName("reward") val reward: Int = 0
) : Parcelable