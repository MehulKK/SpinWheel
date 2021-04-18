package com.macv.wheeldemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macv.pointsitemcard.PointItemCallback
import com.macv.pointsitemcard.PointsItem
import com.macv.todocard.ToDoCallback
import com.macv.todocard.ToDoCardItem
import com.macv.wheeldemo.databinding.ActivityPointsItemCardComponentBinding
import com.macv.wheeldemo.databinding.ActivityTodoCardComponentBinding
import kotlinx.android.synthetic.main.activity_points_item_card_component.*
import kotlinx.android.synthetic.main.activity_points_item_card_component.pointItemCard
import kotlinx.android.synthetic.main.activity_todo_card_component.*

class ToDoCardComponentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindingUtil: ActivityTodoCardComponentBinding = DataBindingUtil.setContentView(this, R.layout.activity_todo_card_component)
        toDoCard.setDynamicList(getPointsItemList(), object : ToDoCallback {
            override fun onToDoClick(mPos: Int, toDoCardItem: ToDoCardItem) {
                Toast.makeText(this@ToDoCardComponentActivity, toDoCardItem.name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getPointsItemList(): ArrayList<ToDoCardItem> {
        val list = ArrayList<ToDoCardItem>()
        list.add(ToDoCardItem(id = 1, emoji = "\uD83D\uDE42", iconBackgroundColor = R.color.colorPrimary, name = "First and Last Name", points = 10))
        list.add(ToDoCardItem(id = 2, emoji = "\uD83D\uDCB0", iconBackgroundColor = R.color.colorPrimary, name = "Verify paycheck", points = 15))
        list.add(ToDoCardItem(id = 3, emoji = "\uD83C\uDFE6", iconBackgroundColor = R.color.colorPrimary, name = "Connect Debit Card", points = 50))
        list.add(ToDoCardItem(id = 4, emoji = "\uD83D\uDE42", iconBackgroundColor = R.color.colorPrimary, name = "First and Last Name", points = 10))
        list.add(ToDoCardItem(id = 5, emoji = "\uD83D\uDCB0", iconBackgroundColor = R.color.colorPrimary, name = "Connect Debit Card", points = 15))
        list.add(ToDoCardItem(id = 6, emoji = "\uD83C\uDFE6", iconBackgroundColor = R.color.colorPrimary, name = "Connect Debit Card", points = 50))
        list.add(ToDoCardItem(id = 7, emoji = "\uD83D\uDE42", iconBackgroundColor = R.color.colorPrimary, name = "First and Last Name", points = 10))
        list.add(ToDoCardItem(id = 8, emoji = "\uD83D\uDCB0", iconBackgroundColor = R.color.colorPrimary, name = "Connect Debit Card", points = 15))
        return list
    }
}