package com.macv.achivement.utils

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountDownItem(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("image") val image: Int? = null,
        @SerializedName("emoji") val emoji: String = ""
) : Parcelable