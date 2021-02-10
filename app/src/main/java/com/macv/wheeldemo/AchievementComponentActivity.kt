package com.macv.wheeldemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macv.achivement.AchievementItem
import com.macv.wheeldemo.databinding.ActivityAchievementCompoenentBinding
import kotlinx.android.synthetic.main.activity_achievement_compoenent.*

class AchievementComponentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindingUtil : ActivityAchievementCompoenentBinding = DataBindingUtil.setContentView(this, R.layout.activity_achievement_compoenent)
        achievement.setDynamicList(getAchievementList())
    }

    private fun getAchievementList(): ArrayList<AchievementItem> {
        val list = ArrayList<AchievementItem>()
        list.add(AchievementItem(id = 1, image = "https://dl.dropboxusercontent.com/s/grmetj2iixvz253/Group%201320.png",
                name = "Watch 30 ads + \uD83C\uDFC6 100 ", max = 40, value = 15, outlineColor = "#2A3137", barColor = "#FDAEB6", backgroundColor = "", textColor = "#FFFFFF"))
        list.add(AchievementItem(id = 2, image = "https://dl.dropboxusercontent.com/s/grmetj2iixvz253/Group%201320.png",
                name = "Scan 20 Reciepts + \uD83C\uDFC6 200 ", max = 30, value = 15, outlineColor = "#2A3137", barColor = "#FDAEB6", backgroundColor = "", textColor = "#FFFFFF"))
        return list
    }
}