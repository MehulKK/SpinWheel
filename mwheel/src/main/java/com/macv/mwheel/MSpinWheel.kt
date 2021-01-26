package com.macv.mwheel

import android.content.Context
import android.widget.FrameLayout
import android.view.View.OnTouchListener
import androidx.annotation.AttrRes
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import java.lang.Exception

class MSpinWheel : FrameLayout, OnTouchListener, OnRotationListener {
    private lateinit var wheelView: MWheelView
    private var arrow: ImageView? = null
    private var target = -1
    private var isRotate = false

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initComponent()
        applyAttribute(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initComponent()
        applyAttribute(attrs)
    }

    private fun initComponent() {
        inflate(context, R.layout.mwheel_layout, this)
        setOnTouchListener(this)
        wheelView = findViewById(R.id.wv_main_wheel)
        wheelView.setOnRotationListener(this)
        arrow = findViewById(R.id.iv_arrow)
    }

    fun addWheelItems(wheelItems: ArrayList<WheelItem>) {
        wheelView.addWheelItems(wheelItems)
    }

    private fun applyAttribute(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LuckyWheel, 0, 0)
        try {
            val backgroundColor = typedArray.getColor(R.styleable.LuckyWheel_background_color, Color.parseColor("#141A1E"))
            val arrowImage = typedArray.getResourceId(R.styleable.LuckyWheel_arrow_image, R.drawable.ic_group_1459)
            val imagePadding = typedArray.getDimensionPixelSize(R.styleable.LuckyWheel_image_padding, 0)
            wheelView.setWheelBackgoundWheel(backgroundColor)
            wheelView.setItemsImagePadding(imagePadding)
            arrow!!.setImageResource(arrowImage)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        typedArray.recycle()
    }

    fun setWheelListener(setWheelListener: WheelListener?) {
        wheelView.setWheelListener(setWheelListener)
        wheelView.setOpenComponent()
    }

    fun setTarget(target: Int) {
        this.target = target
    }

    fun rotateWheelTo(number: Int) {
        if(wheelView.getWheelItems().size>2) {
            isRotate = true
            wheelView.resetRotationLocationToZeroAngle(number)
        }
        else{
            wheelView.setError("Item size is less then 2")
        }
    }

    val SWIPE_DISTANCE_THRESHOLD = 100
    var x1 = 0f
    var x2 = 0f
    var y1 = 0f
    var y2 = 0f
    var dx = 0f
    var dy = 0f
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (target < 0 || isRotate) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x
                y1 = event.y
            }
            MotionEvent.ACTION_UP -> {
                x2 = event.x
                y2 = event.y
                dx = x2 - x1
                dy = y2 - y1
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx < 0 && Math.abs(dx) > SWIPE_DISTANCE_THRESHOLD) rotateWheelTo(target)
                } else {
                    if (dy > 0 && Math.abs(dy) > SWIPE_DISTANCE_THRESHOLD) rotateWheelTo(target)
                }
            }
            else -> return true
        }
        return true
    }

    override fun onFinishRotation() {
        isRotate = false
    }
}