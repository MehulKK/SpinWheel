package com.macv.mwheel

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator

internal class MWheelView : View {
    private var range = RectF()
    private var archPaint: Paint? = null
    private var textPaint: Paint? = null
    private var padding = 0
    private var radius = 0
    private var center = 0
    private var mWheelBackground = 0
    private var mImagePadding = 0
    private lateinit var mWheelItems: ArrayList<WheelItem>
    private var mWheelListener: WheelListener? = null
    private var onRotationListener: OnRotationListener? = null

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    private fun initComponents() {
        //arc paint object
        archPaint = Paint()
        archPaint!!.isAntiAlias = true
        archPaint!!.isDither = true
        //text paint object
        textPaint = Paint()
        textPaint!!.color = Color.WHITE
        textPaint!!.isAntiAlias = true
        textPaint!!.isDither = true
        textPaint!!.textSize = 40f
        //rect rang of the arc
        range = RectF(padding.toFloat(), padding.toFloat(), (padding + radius).toFloat(), (padding + radius).toFloat())
    }

    /**
     * Get the angele of the target
     *
     * @return Number of angle
     */
    private fun getAngleOfIndexTarget(target: Int): Float {
        return (360 / mWheelItems.size * target).toFloat()
    }

    /**
     * Function to set wheel background
     *
     * @param wheelBackground Wheel background color
     */
    fun setWheelBackgoundWheel(wheelBackground: Int) {
        mWheelBackground = wheelBackground
        invalidate()
    }

    fun setItemsImagePadding(imagePadding: Int) {
        mImagePadding = imagePadding
        invalidate()
    }

    /**
     * Function to set wheel listener
     *
     * @param onLuckyWheelReachTheTarget target reach listener
     */
    fun setWheelListener(onLuckyWheelReachTheTarget: WheelListener?) {
        mWheelListener = onLuckyWheelReachTheTarget
    }

    fun setOpenComponent(){
        mWheelListener?.onOpen()
    }

    fun setError(s: String) {
        mWheelListener?.onError(s)
    }

    /**
     * Function to add wheels items
     *
     * @param wheelItems Wheels model item
     */
    fun addWheelItems(wheelItems: ArrayList<WheelItem>) {
        mWheelItems = wheelItems
        invalidate()
    }

    fun getWheelItems() : ArrayList<WheelItem> = mWheelItems

    /**
     * Function to draw wheel background
     *
     * @param canvas Canvas of draw
     */
    private fun drawWheelBackground(canvas: Canvas) {
        val backgroundPainter = Paint()
        backgroundPainter.isAntiAlias = true
        backgroundPainter.isDither = true
        backgroundPainter.color = mWheelBackground
        canvas.drawCircle(center.toFloat(), center.toFloat(), center.toFloat(), backgroundPainter)
    }

    /**
     * Function to draw image in the center of arc
     *
     * @param canvas    Canvas to draw
     * @param tempAngle Temporary angle
     * @param bitmap    Bitmap to draw
     */
    private fun drawImage(canvas: Canvas, tempAngle: Float, bitmap: Bitmap) {
        //get every arc img width and angle
        val imgWidth = radius / mWheelItems.size - mImagePadding
        val angle = ((tempAngle + 360 / mWheelItems.size / 2) * Math.PI / 180).toFloat()
        //calculate x and y
        val x = (center + radius / 2 / 2 * Math.cos(angle.toDouble())).toInt()
        val y = (center + radius / 2 / 2 * Math.sin(angle.toDouble())).toInt()
        //create arc to draw
        val rect = Rect(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth / 2, y + imgWidth / 2)
        //rotate main bitmap
        val px = rect.exactCenterX()
        val py = rect.exactCenterY()
        val matrix = Matrix()
        matrix.postTranslate(-bitmap.width / 2.toFloat(), -bitmap.height / 2.toFloat())
        matrix.postRotate(tempAngle + 120)
        matrix.postTranslate(px, py)
        canvas.drawBitmap(bitmap, matrix, Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG))
        Log.d("sadsdsddssd", bitmap.width.toString() + " : " + bitmap.height)
        matrix.reset()
    }

    /**
     * Function to draw text below image
     *
     * @param canvas     Canvas to draw
     * @param tempAngle  Temporary angle
     * @param sweepAngle current index angle
     * @param text       string to show
     */
    private fun drawText(canvas: Canvas, tempAngle: Float, sweepAngle: Float, text: String) {
        val path = Path()
        path.addArc(range, tempAngle, sweepAngle)
        val textWidth = textPaint!!.measureText(text)
        val hOffset = (radius * Math.PI / mWheelItems!!.size / 2 - textWidth / 2).toInt()
        val vOffset = radius / 2 / 3 - 3
        //canvas.drawText(text, hOffset, vOffset, textPaint);
        canvas.drawTextOnPath(text, path, hOffset.toFloat(), vOffset.toFloat(), textPaint)
    }

    /**
     * Function to rotate wheel to target
     *
     * @param target target number
     */
    fun rotateWheelToTarget(target: Int) {
        val wheelItemCenter = 270 - getAngleOfIndexTarget(target) + 360 / mWheelItems.size / 2
        val DEFAULT_ROTATION_TIME = 9000
        animate().setInterpolator(DecelerateInterpolator())
                .setDuration(DEFAULT_ROTATION_TIME.toLong())
                .rotation(360 * 15 + wheelItemCenter)
                .setListener(object : AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {
                        mWheelListener?.onStart()
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        mWheelListener?.onReward(target - 1)

                        onRotationListener?.onFinishRotation()
                        clearAnimation()
                    }

                    override fun onAnimationCancel(animation: Animator) {}
                    override fun onAnimationRepeat(animation: Animator) {}
                })
                .start()
    }

    /**
     * Function to rotate to zero angle
     *
     * @param target target to reach
     */
    fun resetRotationLocationToZeroAngle(target: Int) {
        animate().setDuration(0)
                .rotation(0f).setListener(object : AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {}
                    override fun onAnimationEnd(animation: Animator) {
                        rotateWheelToTarget(target)
                        clearAnimation()
                    }

                    override fun onAnimationCancel(animation: Animator) {}
                    override fun onAnimationRepeat(animation: Animator) {}
                })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawWheelBackground(canvas)
        initComponents()
        var tempAngle = 0f
        val sweepAngle = 360 / mWheelItems.size.toFloat()
        for (i in mWheelItems.indices) {
            archPaint!!.color = mWheelItems[i].color
            canvas.drawArc(range, tempAngle, sweepAngle, true, archPaint)
            //drawImage(canvas, tempAngle, mWheelItems.get(i).bitmap);
            if (mWheelItems[i].displayText == null) "" else mWheelItems[i].displayText?.let { drawText(canvas, tempAngle, sweepAngle, it) }
            tempAngle += sweepAngle
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = Math.min(measuredWidth, measuredHeight)
        val DEFAULT_PADDING = 25
        padding = if (paddingLeft == 0) DEFAULT_PADDING else paddingLeft
        radius = width - padding * 2
        center = width / 2
        setMeasuredDimension(width, width)
    }

    fun setOnRotationListener(onRotationListener: OnRotationListener?) {
        this.onRotationListener = onRotationListener
    }
}