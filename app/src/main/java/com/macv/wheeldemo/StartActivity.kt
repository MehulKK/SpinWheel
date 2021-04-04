package com.macv.wheeldemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        spin.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        btnCategory.setOnClickListener { startActivity(Intent(this, CategoryComponentActivity::class.java)) }
        btnAchievement.setOnClickListener { startActivity(Intent(this, AchievementComponentActivity::class.java)) }
        btnCountDown.setOnClickListener { startActivity(Intent(this, CountDownComponentActivity::class.java)) }
        btnSweepStakes.setOnClickListener { startActivity(Intent(this, SweepStakesComponentActivity::class.java)) }
    }
}