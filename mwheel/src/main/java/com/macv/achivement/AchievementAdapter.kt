package com.macv.achivement

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.databinding.ViewDataBinding
import com.macv.base.BaseRecyclerAdapter
import com.macv.category.FooterDismissListener
import com.macv.mwheel.R
import com.macv.mwheel.databinding.AchivementListItemBinding


class AchievementAdapter : BaseRecyclerAdapter<AchievementItem>() {
    private lateinit var mFooterDismissListener: FooterDismissListener
    override fun getLayoutIdForType(viewType: Int) = R.layout.achivement_list_item

    override fun onItemClick(view: View?, adapterPosition: Int) {

        when(view?.id){
            R.id.btnClose -> {
                mFooterDismissListener.onClose()
                removeFooter()
            }
        }
    }

    @SuppressLint("Range")
    override fun setDataForListItem(binding: ViewDataBinding, dataModel: AchievementItem) {
        super.setDataForListItem(binding, dataModel)
        val achivementListItemBinding : AchivementListItemBinding = binding as AchivementListItemBinding
        achivementListItemBinding.itemView.setBackgroundResource(R.drawable.achivement_default_bg)

        val drawable : GradientDrawable = achivementListItemBinding.itemView.getBackground() as GradientDrawable
        drawable.setStroke(2, Color.parseColor(dataModel.outlineColor))
        drawable.setColor(Color.parseColor(dataModel.barColor))

        achivementListItemBinding.txtAchievement.setTextColor(Color.parseColor(dataModel.textColor))
    }

    override fun areItemsSame(oldItem: AchievementItem, newItem: AchievementItem): Boolean {
        return oldItem.id == newItem.id
    }
}