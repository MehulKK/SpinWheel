package com.macv.achivement

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AchievementItem(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("image") val image: Int? = null,
        @SerializedName("emoji") val emoji: String = "",
        @SerializedName("action") val action: String = "",
        @SerializedName("points") val points: Int = 0,
        @SerializedName("max") val max: Int = 0,
        @SerializedName("value") val value: Int = 0,
        @SerializedName("outlineColor") val outlineColor: String = "",
        @SerializedName("barColor") val barColor: String = "",
        @SerializedName("backgroundColor") val backgroundColor: String = "",
        @SerializedName("textColor") val textColor: String = ""
) : Parcelable