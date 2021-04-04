package com.macv.sweepstakes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.macv.mwheel.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SweepStakesItem(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("title") val title: String = "",
        @SerializedName("description") val description : String = "",
        @SerializedName("pointsPerEntry") val pointsPerEntry: Int = 0,
        @SerializedName("entires") val entires: Int = 0,
        @SerializedName("timestamp") val timestamp: Long = 0L,
        @SerializedName("maxUnit") val maxUnit: String = ""
) : Parcelable