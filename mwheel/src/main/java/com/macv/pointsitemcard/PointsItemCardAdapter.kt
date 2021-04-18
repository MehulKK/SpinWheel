package com.macv.pointsitemcard

import android.content.Context
import android.view.View
import com.macv.achivement.AchievementMaxListener
import com.macv.base.BaseRecyclerAdapter
import com.macv.mwheel.R


class PointsItemCardAdapter(private val context: Context, private val mOrientation: Int) : BaseRecyclerAdapter<PointsItem>() {
    private lateinit var mPointItemCallback: PointItemCallback
    override fun getLayoutIdForType(viewType: Int) = if (mOrientation == 0 ) R.layout.item_points_horizontal else R.layout.item_points_vertical

    override fun onItemClick(view: View?, adapterPosition: Int) {

        when (view?.id) {
            R.id.layoutContainer -> {
                mPointItemCallback.onPointItemClick(adapterPosition, arrayList[adapterPosition])
            }
        }
    }

    override fun areItemsSame(oldItem: PointsItem, newItem: PointsItem): Boolean {
        return oldItem.id == newItem.id
    }

    fun setCallBackListener(pointItemCallback: PointItemCallback) {
        mPointItemCallback = pointItemCallback
    }
}