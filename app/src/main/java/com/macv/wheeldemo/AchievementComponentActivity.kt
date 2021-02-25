package com.macv.wheeldemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macv.achivement.AchievementItem
import com.macv.achivement.AchievementMaxListener
import com.macv.wheeldemo.databinding.ActivityAchievementCompoenentBinding
import kotlinx.android.synthetic.main.activity_achievement_compoenent.*

class AchievementComponentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindingUtil : ActivityAchievementCompoenentBinding = DataBindingUtil.setContentView(this, R.layout.activity_achievement_compoenent)
        achievement.setDynamicList(getAchievementList())
        achievement.setMaxValueListener(object : AchievementMaxListener{
            override fun onClick(achievementItem: AchievementItem) {
                Toast.makeText(this@AchievementComponentActivity, achievementItem.action+" Item Click", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getAchievementList(): ArrayList<AchievementItem> {
        val list = ArrayList<AchievementItem>()
        list.add(AchievementItem(id = 1, image = null, emoji = "\uD83D\uDCFA", points = 100,
                action = "Watch 30 ads", max = 40, value = 15, outlineColor = "#FF0000", barColor = "#FDAEB6", backgroundColor = "#2A3137", textColor = "#FFFFFF"))
        list.add(AchievementItem(id = 2, image = R.drawable.ic_close, emoji = "\uD83D\uDCFA", points = 200,
                action = "Scan 20 Reciepts", max = 30, value = 30, outlineColor = "#00FF00", barColor = "#FDAEB6", backgroundColor = "", textColor = "#FFFFFF"))
        return list
    }
}