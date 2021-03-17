package com.macv.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.macv.mwheel.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryItem(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("emoji") val emoji: String = "",
        @SerializedName("iconBackgroundColor") val iconBackgroundColor : Int = R.color.category_image_bg,
        @SerializedName("name") val name: String = "",
        @SerializedName("amount") val amount: Double = 0.0,
        @SerializedName("avgPercent") val avgPercent: String = "",
        @SerializedName("yourPercent") val yourPercent: String = "",
        @SerializedName("avgTextColor") val avgTextColor: Int = R.color.textPrimary,
        @SerializedName("youTextColor") val youTextColor: Int = R.color.textPrimary,
        @SerializedName("monthlyOverSpendDollar") val monthlyOverSpendDollar: String = ""
) : Parcelable