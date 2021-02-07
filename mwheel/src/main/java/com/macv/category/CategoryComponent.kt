package com.macv.category

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.macv.mwheel.R


class CategoryComponent : RecyclerView{

    private var categoryList = ArrayList<CategoryItem>()

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
        //initDefaultLayoutManager(attrs, defStyleAttr)
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CategoryComponent, defStyleAttr, 0)
            /*initItemSpace(a)
            initDivider(a)
            initEmptyView(a)
            initPagination(a)
            initStickyMode(a)*/
            a.recycle()
        }
    }

    private fun initDefaultLayoutManager(attrs: AttributeSet?, defStyleAttr: Int) {
        layoutManager?.let {
            layoutManager = LinearLayoutManager(context, attrs, defStyleAttr, 0)
        }
    }

    fun setDynamicList(list : ArrayList<CategoryItem>){
        categoryList = list
        val categoryAdapter = CategoryAdapter()
        adapter = categoryAdapter
        categoryAdapter.setList(categoryList)
    }

    /*override fun setAdapter(adapter: Adapter<*>?) {
        val categoryAdapter = CategoryAdapter()
        categoryAdapter.setList(categoryList)
        super.setAdapter(adapter)
    }*/
}