package com.macv.todocard

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.ViewDataBinding
import com.macv.achivement.AchievementMaxListener
import com.macv.base.BaseRecyclerAdapter
import com.macv.category.CategoryItem
import com.macv.mwheel.R
import com.macv.mwheel.databinding.ItemTodoCardBinding
import com.macv.mwheel.databinding.ListItemBinding
import com.macv.pointsitemcard.PointItemCallback
import com.macv.pointsitemcard.PointsItem


class ToDoCardAdapter(private val context: Context) : BaseRecyclerAdapter<ToDoCardItem>() {
    private lateinit var mToDoCallback: ToDoCallback
    override fun getLayoutIdForType(viewType: Int) = R.layout.item_todo_card

    override fun onItemClick(view: View?, adapterPosition: Int) {
        when (view?.id) {
            R.id.layoutContainer -> {
                mToDoCallback.onToDoClick(adapterPosition, arrayList[adapterPosition])
            }
        }
    }

    override fun setDataForListItem(binding: ViewDataBinding, dataModel: ToDoCardItem) {
        super.setDataForListItem(binding, dataModel)
        if(binding is ItemTodoCardBinding) {
            val itemTodoCardBinding : ItemTodoCardBinding = binding
            val unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_category_image_placeholder)
            unwrappedDrawable?.let {
                val wrappedDrawable: Drawable = DrawableCompat.wrap(unwrappedDrawable)
                DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(context, dataModel.iconBackgroundColor))
                itemTodoCardBinding.imgCategory.background = wrappedDrawable
            }
        }
    }

    override fun areItemsSame(oldItem: ToDoCardItem, newItem: ToDoCardItem): Boolean {
        return oldItem.id == newItem.id
    }

    fun setCallBackListener(toDoCallback: ToDoCallback) {
        mToDoCallback = toDoCallback
    }
}