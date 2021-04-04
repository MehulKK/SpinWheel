package com.macv.wheeldemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macv.countdown.CountdownView
import com.macv.sweepstakes.SweepStakesItem
import com.macv.wheeldemo.databinding.ActivityCountdownCompoenentBinding
import com.macv.wheeldemo.databinding.ActivitySweepStakesComponentBinding
import kotlinx.android.synthetic.main.activity_achievement_compoenent.*
import kotlinx.android.synthetic.main.activity_countdown_compoenent.*
import kotlinx.android.synthetic.main.activity_sweep_stakes_component.*

class SweepStakesComponentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindingUtil : ActivitySweepStakesComponentBinding = DataBindingUtil.setContentView(this, R.layout.activity_sweep_stakes_component)
        val sweepStakesItem = SweepStakesItem(id = 1, title = "Enter to Win \$1000",
                            description = "One \$500 winner · Five \$100 winners.",
                            pointsPerEntry = 50, entires = 5, timestamp = (2 * 60 * 60 * 1000), maxUnit = "HOUR")
        sweepStakesCard.setSweepStakesItem(sweepStakesItem)
    }
}