package com.macv.wheeldemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val start = findViewById<Button>(R.id.spin)
        start.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }
}