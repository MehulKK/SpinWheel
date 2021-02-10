package com.macv.achivement

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ViewDataBinding
import com.macv.base.BaseRecyclerAdapter
import com.macv.category.FooterDismissListener
import com.macv.mwheel.R
import com.macv.mwheel.databinding.AchievementListItemBinding


class AchievementAdapter : BaseRecyclerAdapter<AchievementItem>() {
    private lateinit var mFooterDismissListener: FooterDismissListener
    override fun getLayoutIdForType(viewType: Int) = R.layout.achievement_list_item

    override fun onItemClick(view: View?, adapterPosition: Int) {

        when (view?.id) {
            R.id.btnClose -> {
                mFooterDismissListener.onClose()
                removeFooter()
            }
        }
    }

    @SuppressLint("Range")
    override fun setDataForListItem(binding: ViewDataBinding, dataModel: AchievementItem) {
        super.setDataForListItem(binding, dataModel)
        val achivementListItemBinding: AchievementListItemBinding = binding as AchievementListItemBinding
        achivementListItemBinding.itemView.setBackgroundResource(R.drawable.achivement_default_bg)

        val drawable: GradientDrawable = achivementListItemBinding.itemView.background as GradientDrawable
        drawable.setStroke(5, Color.parseColor(dataModel.outlineColor))


        val drawableProgress: GradientDrawable = achivementListItemBinding.itemProgress.background as GradientDrawable
        drawableProgress.setColor(Color.parseColor(dataModel.barColor))

        val paramsProcess: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(0, ConstraintLayout.LayoutParams.MATCH_PARENT, ((dataModel.value*100) / dataModel.max).toFloat())
        achivementListItemBinding.itemProgress.layoutParams = paramsProcess


        achivementListItemBinding.itemView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                achivementListItemBinding.itemView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val height = achivementListItemBinding.itemView.height //height is ready
                val radius = (height/ 2).toFloat()
                drawable.cornerRadius = radius
                drawableProgress.cornerRadii = floatArrayOf(radius, radius, 0f, radius, radius, 0f, radius, radius)
            }
        })


        achivementListItemBinding.txtAchievement.setTextColor(Color.parseColor(dataModel.textColor))
    }

    override fun areItemsSame(oldItem: AchievementItem, newItem: AchievementItem): Boolean {
        return oldItem.id == newItem.id
    }
}