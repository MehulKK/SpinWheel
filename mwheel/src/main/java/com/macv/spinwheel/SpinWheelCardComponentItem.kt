package com.macv.spinwheel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.macv.mwheel.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpinWheelCardComponentItem(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("maxRewardPoints") val maxRewardPoints: Int = 0,
        @SerializedName("pointsPerSpin") val pointsPerSpin: Int = 0
) : Parcelable