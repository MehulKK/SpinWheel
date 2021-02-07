package com.macv.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryItem(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("emoji") val emoji: String = "",
        @SerializedName("name") val name: String = "",
        @SerializedName("avgPercent") val avgPercent: String = "",
        @SerializedName("yourPercent") val yourPercent: String = "",
        @SerializedName("monthlyOverSpendDollar") val monthlyOverSpendDollar: String = ""
) : Parcelable