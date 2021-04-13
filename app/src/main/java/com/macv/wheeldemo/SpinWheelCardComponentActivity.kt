package com.macv.wheeldemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.macv.countdown.CountdownView
import com.macv.spinwheel.SpinWheelCardCallBack
import com.macv.spinwheel.SpinWheelCardComponentItem
import com.macv.sweepstakes.SweepStakesCallBack
import com.macv.sweepstakes.SweepStakesItem
import com.macv.wheeldemo.databinding.ActivityCountdownCompoenentBinding
import com.macv.wheeldemo.databinding.ActivitySpinWheelCardComponentBindingImpl
import com.macv.wheeldemo.databinding.ActivitySweepStakesComponentBinding
import kotlinx.android.synthetic.main.activity_achievement_compoenent.*
import kotlinx.android.synthetic.main.activity_countdown_compoenent.*
import kotlinx.android.synthetic.main.activity_spin_wheel_card_component.*
import kotlinx.android.synthetic.main.activity_sweep_stakes_component.*
import kotlinx.android.synthetic.main.activity_sweep_stakes_component.sweepStakesCard

class SpinWheelCardComponentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBindingUtil : ActivitySpinWheelCardComponentBindingImpl = DataBindingUtil.setContentView(this, R.layout.activity_spin_wheel_card_component)
        val spinWheelCardComponentItem = SpinWheelCardComponentItem(id = 1, maxRewardPoints = 500, pointsPerSpin = 50)
        spinWheelCard.setSpinWheelCardItem(spinWheelCardComponentItem)
        spinWheelCard.setCallBack(object : SpinWheelCardCallBack{
            override fun onSpinWheelCardClick() {
                Toast.makeText(this@SpinWheelCardComponentActivity, "Item Click", Toast.LENGTH_LONG).show()
            }
        })
    }
}