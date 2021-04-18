package com.macv.todocard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.macv.mwheel.R
import com.macv.pointsitemcard.PointItemCallback
import com.macv.pointsitemcard.PointsItem
import com.macv.pointsitemcard.PointsItemCardAdapter
import com.macv.sweepstakes.SweepStakesCallBack
import com.macv.sweepstakes.SweepStakesItem
import kotlinx.android.synthetic.main.sweepstakes_card_componenet.view.*
import kotlinx.android.synthetic.main.todo_card.view.*

class ToDoCardComponent @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var mToDoCardList = ArrayList<ToDoCardItem>()
    private lateinit var mToDoCardAdapter: ToDoCardAdapter

    init {
        LayoutInflater.from(context).inflate(R.layout.todo_card, this, true)
        initAdapter()
    }

    private fun initAdapter(){
        mToDoCardAdapter = ToDoCardAdapter(context)
        recyclerView.adapter = mToDoCardAdapter
    }

    fun setDynamicList(list : ArrayList<ToDoCardItem>, toDoCallback: ToDoCallback){
        mToDoCardList = list
        mToDoCardAdapter.setList(mToDoCardList)
        mToDoCardAdapter.setCallBackListener(toDoCallback)
    }
}
