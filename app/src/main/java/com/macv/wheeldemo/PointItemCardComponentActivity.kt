package com.macv.wheeldemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macv.pointsitemcard.PointItemCallback
import com.macv.pointsitemcard.PointsItem
import com.macv.wheeldemo.databinding.ActivityPointsItemCardComponentBinding
import kotlinx.android.synthetic.main.activity_points_item_card_component.*

class PointItemCardComponentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindingUtil: ActivityPointsItemCardComponentBinding = DataBindingUtil.setContentView(this, R.layout.activity_points_item_card_component)
        pointItemCard.setDynamicList(getPointsItemList(), object : PointItemCallback {
            override fun onPointItemClick(mPosition: Int, pointsItem: PointsItem) {
                Toast.makeText(this@PointItemCardComponentActivity, pointsItem.title, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getPointsItemList(): ArrayList<PointsItem> {
        val list = ArrayList<PointsItem>()
        list.add(PointsItem(id = 1, image = R.drawable.ic_phone, title = "Watch a Video Ad", description = "Get points by watching a short video clip.", reward = 10))
        list.add(PointsItem(id = 2, image = R.drawable.ic_receipt, title = "Second Ad", description = "Second desc", reward = 20))
        list.add(PointsItem(id = 3, image = R.drawable.ic_phone, title = "Watch a Video Ad", description = "Get points by watching a short video clip.", reward = 30))
        list.add(PointsItem(id = 4, image = R.drawable.ic_receipt, title = "Fourth Ad", description = "Fourth desc", reward = 40))
        list.add(PointsItem(id = 5, image = R.drawable.ic_phone, title = "Watch a Video Ad", description = "Get points by watching a short video clip.", reward = 50))
        list.add(PointsItem(id = 6, image = R.drawable.ic_receipt, title = "Sixth Ad", description = "Sixth desc", reward = 60))
        list.add(PointsItem(id = 7, image = R.drawable.ic_phone, title = "Watch a Video Ad", description = "Get points by watching a short video clip.", reward = 70))
        return list
    }
}