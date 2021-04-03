package com.macv.wheeldemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macv.countdown.CountdownView
import com.macv.wheeldemo.databinding.ActivityCountdownCompoenentBinding
import kotlinx.android.synthetic.main.activity_achievement_compoenent.*
import kotlinx.android.synthetic.main.activity_countdown_compoenent.*

class CountDownComponentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindingUtil : ActivityCountdownCompoenentBinding = DataBindingUtil.setContentView(this, R.layout.activity_countdown_compoenent)

        val time4 = 2.toLong() * 24 * 60 * 60 * 1000
        countDown.start(time4)
        countDown.setMaxUnit("DAY")
        countDown.setOnCountdownEndListener(object : CountdownView.OnCountdownEndListener {
            override fun onEnd(cv: CountdownView?) {
                Toast.makeText(this@CountDownComponentActivity, "Finish", Toast.LENGTH_LONG);
            }
        })
    }
}