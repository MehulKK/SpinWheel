package com.macv.achivement

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.macv.category.CategoryAdapter
import com.macv.category.CategoryItem
import com.macv.category.FooterDismissListener
import com.macv.mwheel.R


class AchievementComponent : RecyclerView{

    private var achievementItem = ArrayList<AchievementItem>()
    private lateinit var achievementAdapter : AchievementAdapter
    private lateinit var mContext : Context

    constructor(context: Context) : super(context){
        init(context, null, 0);
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context, attrs, 0);
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context, attrs, defStyleAttr);
    }

    private fun init(context: Context, @Nullable attrs: AttributeSet?, defStyleAttr: Int) {
        mContext = context
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CategoryComponent, defStyleAttr, 0)
            a.recycle()
        }

        initAdapter()
    }

    private fun initAdapter(){
        achievementAdapter = AchievementAdapter(mContext)
        adapter = achievementAdapter
    }

    fun setDynamicList(list : ArrayList<AchievementItem>){
        achievementItem = list
        achievementAdapter.setList(achievementItem)
    }

    fun setMaxValueListener(achievementMaxListener: AchievementMaxListener){
        achievementAdapter.setMaxValueListener(achievementMaxListener)
    }
}