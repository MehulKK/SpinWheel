package com.macv.pointsitemcard

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.macv.achivement.AchievementMaxListener
import com.macv.mwheel.R


class PointsItemCardComponent : RecyclerView {

    private var mPointsItemList = ArrayList<PointsItem>()
    private lateinit var mPointsItemCardAdapter: PointsItemCardAdapter
    private lateinit var mContext : Context
    private var mOrientation = 0;

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
            val a = context.obtainStyledAttributes(attrs, R.styleable.PointsItemCardComponent, defStyleAttr, 0)
            mOrientation = a.getInt(R.styleable.PointsItemCardComponent_layoutOrientation, 0)
            a.recycle()
        }
        layoutManager = LinearLayoutManager(context, mOrientation, false)
        initAdapter()
    }

    private fun initAdapter(){
        mPointsItemCardAdapter = PointsItemCardAdapter(mContext, mOrientation)
        adapter = mPointsItemCardAdapter
    }

    fun setDynamicList(list : ArrayList<PointsItem>, pointItemCallback: PointItemCallback){
        mPointsItemList = list
        mPointsItemCardAdapter.setList(mPointsItemList)
        mPointsItemCardAdapter.setCallBackListener(pointItemCallback)
    }
}