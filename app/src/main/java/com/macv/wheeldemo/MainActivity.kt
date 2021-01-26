package com.macv.wheeldemo

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.macv.mwheel.MSpinWheel
import com.macv.mwheel.WheelItem
import com.macv.mwheel.WheelListener
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var lw: MSpinWheel
    private lateinit var wheelItems: ArrayList<WheelItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateWheelItems()
        lw = findViewById(R.id.lwv)
        lw.addWheelItems(wheelItems)
        lw.setWheelListener(object : WheelListener {
            override fun onOpen() {
                Toast.makeText(this@MainActivity, "Open ", Toast.LENGTH_LONG).show()
            }
            override fun onClose() {

            }
            override fun onReward(pos: Int) {
                Toast.makeText(this@MainActivity, "Target Reached " + wheelItems[pos].displayText, Toast.LENGTH_LONG).show()
            }

            override fun onStart() {
                Toast.makeText(this@MainActivity, "Start ", Toast.LENGTH_LONG).show()
            }

            override fun onError(s: String) {
                Toast.makeText(this@MainActivity, s, Toast.LENGTH_LONG).show()
            }
        })
        val start = findViewById<Button>(R.id.start)
        start.setOnClickListener { lw.rotateWheelTo(Random().nextInt(wheelItems.size)) }
    }

    private fun generateWheelItems() {
        wheelItems = ArrayList()
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "1 $", 1, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "2 $", 2, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "3 $", 3, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "4 $", 4, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "5 $", 5, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "6 $", 6, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "7 $", 7, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "8 $", 8, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "9 $", 9, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "10 $", 10, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "11 $", 11, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "12 $", 12, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "13 $", 13, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "14 $", 14, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "15 $", 15, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "16 $", 16, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "17 $", 17, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "18 $", 18, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#141A1E"), "19 $", 19, "USD"))
        wheelItems.add(WheelItem(Color.parseColor("#040D14"), "20 $", 20, "USD"))
    }
}