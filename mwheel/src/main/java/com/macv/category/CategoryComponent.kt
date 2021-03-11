package com.macv.category

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.macv.mwheel.R


class CategoryComponent : RecyclerView{

    private var categoryList = ArrayList<CategoryItem>()
    private var isFooter: Boolean = false
    private var isHeader: Boolean = false
    private lateinit var categoryAdapter : CategoryAdapter
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
            isFooter = a.getBoolean(R.styleable.CategoryComponent_showFooter, false)
            isHeader = a.getBoolean(R.styleable.CategoryComponent_showHeader, true)
            a.recycle()
        }

        initAdapter()
    }

    private fun initAdapter(){
        categoryAdapter = CategoryAdapter(mContext)
        adapter = categoryAdapter
    }

    fun setDynamicList(list : ArrayList<CategoryItem>){
        categoryList = list
        categoryAdapter.setList(categoryList)

        if(isHeader)
            categoryAdapter.addHeader()

        if(isFooter) {
            categoryAdapter.addFooter()
        }
    }

    fun setFooterCloseListener(footerDismissListener: FooterDismissListener){
        categoryAdapter.setFooterListener(footerDismissListener)
    }
}