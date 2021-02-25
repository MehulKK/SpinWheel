package com.macv.achivement

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.macv.achivement.utils.DefaultBootstrapBrand
import com.macv.achivement.utils.ValueUpdateListener
import com.macv.base.BaseRecyclerAdapter
import com.macv.category.FooterDismissListener
import com.macv.mwheel.R
import com.macv.mwheel.databinding.AchievementListItemBinding


class AchievementAdapter(private val context : Context) : BaseRecyclerAdapter<AchievementItem>() {
    private lateinit var mAchievementMaxListener : AchievementMaxListener
    override fun getLayoutIdForType(viewType: Int) = R.layout.achievement_list_item

    override fun onItemClick(view: View?, adapterPosition: Int) {

        when (view?.id) {
            R.id.layoutContainer -> {
                mAchievementMaxListener.onClick(arrayList[adapterPosition])
            }
        }
    }

    @SuppressLint("Range")
    override fun setDataForListItem(binding: ViewDataBinding, dataModel: AchievementItem) {
        super.setDataForListItem(binding, dataModel)
        val achievementListItemBinding: AchievementListItemBinding = binding as AchievementListItemBinding

        achievementListItemBinding.exampleProgressChange.progress = ((dataModel.value * 100) / dataModel.max)


        val backgroundColor = if (dataModel.backgroundColor.isEmpty()) ContextCompat.getColor(context, R.color.achievement_item_background) else Color.parseColor(dataModel.backgroundColor)
        val barColor = if (dataModel.barColor.isEmpty()) ContextCompat.getColor(context, R.color.achievement_item_progress) else Color.parseColor(dataModel.barColor)
        val outlineColor = if (dataModel.outlineColor.isEmpty()) ContextCompat.getColor(context, R.color.achievement_item_stroke) else Color.parseColor(dataModel.outlineColor)
        val textColor = if (dataModel.textColor.isEmpty()) ContextCompat.getColor(context, R.color.achievement_item_text) else Color.parseColor(dataModel.textColor)

        achievementListItemBinding.txtAchievement.setTextColor(textColor)

        achievementListItemBinding.exampleProgressChange.setProgressbarColor(DefaultBootstrapBrand.DANGER, barColor, outlineColor, backgroundColor)
        achievementListItemBinding.exampleProgressChange.setValueUpdateListener(object : ValueUpdateListener {
            override fun value(value: Int) {
                if (value >= dataModel.max) {
                    achievementListItemBinding.txtAchievement.text = String.format(context.getString(R.string.success_action), dataModel.points)
                } else {
                    achievementListItemBinding.txtAchievement.text = String.format(context.getString(R.string.default_action), dataModel.action, dataModel.points)
                }
            }
        })
    }

    override fun areItemsSame(oldItem: AchievementItem, newItem: AchievementItem): Boolean {
        return oldItem.id == newItem.id
    }

    fun setMaxValueListener(achievementMaxListener: AchievementMaxListener) {
        mAchievementMaxListener = achievementMaxListener
    }
}