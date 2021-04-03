package com.macv.countdown

import android.content.Context
import android.graphics.Canvas
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import com.macv.mwheel.R

class CountdownView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val mCountdown: BaseCountdown
    private var mCustomCountDownTimer: CustomCountDownTimer? = null
    private var mOnCountdownEndListener: OnCountdownEndListener? = null
    private var mOnCountdownIntervalListener: OnCountdownIntervalListener? = null
    private val isHideTimeBackground: Boolean
    private var mPreviousIntervalCallbackTime: Long = 0
    private var mInterval: Long = 0

    /**
     * get remain time
     * @return remain time ( millisecond )
     */
    var remainTime: Long = 0
        private set

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val contentAllWidth: Int = mCountdown.allContentWidth
        val contentAllHeight: Int = mCountdown.allContentHeight
        val viewWidth = measureSize(1, contentAllWidth, widthMeasureSpec) + 10
        val viewHeight = measureSize(2, contentAllHeight, heightMeasureSpec) + 15
        setMeasuredDimension(viewWidth, viewHeight)
        mCountdown.onMeasure(this, viewWidth, viewHeight, contentAllWidth, contentAllHeight)
    }

    /**
     * measure view Size
     * @param specType    1 width 2 height
     * @param contentSize all content view size
     * @param measureSpec spec
     * @return measureSize
     */
    private fun measureSize(specType: Int, contentSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = Math.max(contentSize, specSize)
        } else {
            result = contentSize
            result += if (specType == 1) {
                // width
                paddingLeft + paddingRight
            } else {
                // height
                paddingTop + paddingBottom
            }
        }
        return result
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mCountdown.onDraw(canvas)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stop()
    }

    private fun reLayout() {
        mCountdown.reLayout()
        requestLayout()
    }

    /**
     * start countdown
     * @param millisecond millisecond
     */
    fun start(millisecond: Long) {
        if (millisecond <= 0) return
        mPreviousIntervalCallbackTime = 0
        mCustomCountDownTimer?.let {
            it.stop()
            mCustomCountDownTimer = null
        }

        val countDownInterval: Long
        if (mCountdown.isShowMillisecond) {
            countDownInterval = 10
            updateShow(millisecond)
        } else {
            countDownInterval = 1000
        }
        mCustomCountDownTimer = object : CustomCountDownTimer(millisecond, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                updateShow(millisUntilFinished)
            }

            override fun onFinish() {
                // countdown end
                allShowZero()
                // callback
                if (null != mOnCountdownEndListener) {
                    mOnCountdownEndListener!!.onEnd(this@CountdownView)
                }
            }
        }
        mCustomCountDownTimer?.start()
    }

    /**
     * stop countdown
     */
    fun stop() {
        mCustomCountDownTimer?.stop()
    }

    /**
     * pause countdown
     */
    fun pause() {
        mCustomCountDownTimer?.pause()
    }

    /**
     * pause countdown
     */
    fun restart() {
        mCustomCountDownTimer?.restart()
    }

    /**
     * custom time show
     * @param isShowDay isShowDay
     * @param isShowHour isShowHour
     * @param isShowMinute isShowMinute
     * @param isShowSecond isShowSecond
     * @param isShowMillisecond isShowMillisecond
     *
     * use:[.dynamicShow]
     */
    @Deprecated("")
    fun customTimeShow(isShowDay: Boolean, isShowHour: Boolean, isShowMinute: Boolean, isShowSecond: Boolean, isShowMillisecond: Boolean) {
        mCountdown.mHasSetIsShowDay = true
        mCountdown.mHasSetIsShowHour = true
        val isModCountdownInterval = mCountdown.refTimeShow(isShowDay, isShowHour, isShowMinute, isShowSecond, isShowMillisecond)

        // judgement modify countdown interval
        if (isModCountdownInterval) {
            start(remainTime)
        }
    }

    /**
     * set all time zero
     */
    fun allShowZero() {
        mCountdown.setTimes(0, 0, 0, 0, 0)
        invalidate()
    }

    /**
     * set countdown end callback listener
     * @param onCountdownEndListener OnCountdownEndListener
     */
    fun setOnCountdownEndListener(onCountdownEndListener: OnCountdownEndListener?) {
        mOnCountdownEndListener = onCountdownEndListener
    }

    /**
     * set interval callback listener
     * @param interval interval time
     * @param onCountdownIntervalListener OnCountdownIntervalListener
     */
    fun setOnCountdownIntervalListener(interval: Long, onCountdownIntervalListener: OnCountdownIntervalListener?) {
        mInterval = interval
        mOnCountdownIntervalListener = onCountdownIntervalListener
    }

    fun updateShow(ms: Long) {
        remainTime = ms
        reSetTime(ms)

        // interval callback
        if (mInterval > 0 && null != mOnCountdownIntervalListener) {
            if (mPreviousIntervalCallbackTime == 0L) {
                mPreviousIntervalCallbackTime = ms
            } else if (ms + mInterval <= mPreviousIntervalCallbackTime) {
                mPreviousIntervalCallbackTime = ms
                mOnCountdownIntervalListener!!.onInterval(this, remainTime)
            }
        }
        if (mCountdown.handlerAutoShowTime() || mCountdown.handlerDayLargeNinetyNine()) {
            reLayout()
        } else {
            invalidate()
        }
    }

    private fun reSetTime(ms: Long) {
        var day = 0
        val hour: Int
        if (!mCountdown.isConvertDaysToHours) {
            day = (ms / (1000 * 60 * 60 * 24)).toInt()
            hour = (ms % (1000 * 60 * 60 * 24) / (1000 * 60 * 60)).toInt()
        } else {
            hour = (ms / (1000 * 60 * 60)).toInt()
        }
        val minute = (ms % (1000 * 60 * 60) / (1000 * 60)).toInt()
        val second = (ms % (1000 * 60) / 1000).toInt()
        val millisecond = (ms % 1000).toInt()
        mCountdown.setTimes(day, hour, minute, second, millisecond)
    }

    interface OnCountdownEndListener {
        fun onEnd(cv: CountdownView?)
    }

    interface OnCountdownIntervalListener {
        fun onInterval(cv: CountdownView?, remainTime: Long)
    }

    /**
     * Dynamic show
     * @param dynamicConfig DynamicConfig
     */
    fun dynamicShow(dynamicConfig: DynamicConfig?) {
        if (null == dynamicConfig) return
        var isReLayout = false
        var isInvalidate = false
        val timeTextSize: Float = dynamicConfig.getTimeTextSize()
        if (null != timeTextSize) {
            mCountdown.setTimeTextSize(timeTextSize)
            isReLayout = true
        }
        val suffixTextSize: Float = dynamicConfig.getSuffixTextSize()
        if (null != suffixTextSize) {
            mCountdown.setSuffixTextSize(suffixTextSize)
            isReLayout = true
        }
        val timeTextColor: Int = dynamicConfig.getTimeTextColor()
        if (null != timeTextColor) {
            mCountdown.setTimeTextColor(timeTextColor)
            isInvalidate = true
        }
        val suffixTextColor: Int = dynamicConfig.getSuffixTextColor()
        if (null != suffixTextColor) {
            mCountdown.setSuffixTextColor(suffixTextColor)
            isInvalidate = true
        }
        val isTimeTextBold: Boolean = dynamicConfig.isTimeTextBold()
        if (null != isTimeTextBold) {
            mCountdown.setTimeTextBold(isTimeTextBold)
            isReLayout = true
        }
        val isSuffixTimeTextBold: Boolean = dynamicConfig.isSuffixTimeTextBold()
        if (null != isSuffixTimeTextBold) {
            mCountdown.setSuffixTextBold(isSuffixTimeTextBold)
            isReLayout = true
        }

        // suffix text (all)
        val suffix: String = dynamicConfig.getSuffix()
        if (!TextUtils.isEmpty(suffix)) {
            mCountdown.setSuffix(suffix)
            isReLayout = true
        }

        // suffix text
        val suffixDay: String = dynamicConfig.getSuffixDay()
        val suffixHour: String = dynamicConfig.getSuffixHour()
        val suffixMinute: String = dynamicConfig.getSuffixMinute()
        val suffixSecond: String = dynamicConfig.getSuffixSecond()
        val suffixMillisecond: String = dynamicConfig.getSuffixMillisecond()
        if (mCountdown.setSuffix(suffixDay, suffixHour, suffixMinute, suffixSecond, suffixMillisecond)) {
            isReLayout = true
        }

        // suffix margin (all)
        val suffixLRMargin: Float = dynamicConfig.getSuffixLRMargin()
        if (null != suffixLRMargin) {
            mCountdown.setSuffixLRMargin(suffixLRMargin)
            isReLayout = true
        }

        // suffix margin
        val suffixDayLeftMargin: Float = dynamicConfig.getSuffixDayLeftMargin()
        val suffixDayRightMargin: Float = dynamicConfig.getSuffixDayRightMargin()
        val suffixHourLeftMargin: Float = dynamicConfig.getSuffixHourLeftMargin()
        val suffixHourRightMargin: Float = dynamicConfig.getSuffixHourRightMargin()
        val suffixMinuteLeftMargin: Float = dynamicConfig.getSuffixMinuteLeftMargin()
        val suffixMinuteRightMargin: Float = dynamicConfig.getSuffixMinuteRightMargin()
        val suffixSecondLeftMargin: Float = dynamicConfig.getSuffixSecondLeftMargin()
        val suffixSecondRightMargin: Float = dynamicConfig.getSuffixSecondRightMargin()
        val suffixMillisecondRightMargin: Float = dynamicConfig.getSuffixMillisecondLeftMargin()
        if (mCountdown.setSuffixMargin(suffixDayLeftMargin, suffixDayRightMargin, suffixHourLeftMargin, suffixHourRightMargin,
                        suffixMinuteLeftMargin, suffixMinuteRightMargin, suffixSecondLeftMargin, suffixSecondRightMargin, suffixMillisecondRightMargin)) {
            isReLayout = true
        }
        val suffixGravity: Int = dynamicConfig.getSuffixGravity()
        if (null != suffixGravity) {
            mCountdown.setSuffixGravity(suffixGravity)
            isReLayout = true
        }

        // judgement time show
        val tempIsShowDay: Boolean = dynamicConfig.isShowDay()
        val tempIsShowHour: Boolean = dynamicConfig.isShowHour()
        val tempIsShowMinute: Boolean = dynamicConfig.isShowMinute()
        val tempIsShowSecond: Boolean = dynamicConfig.isShowSecond()
        val tempIsShowMillisecond: Boolean = dynamicConfig.isShowMillisecond()
        if (null != tempIsShowDay || null != tempIsShowHour || null != tempIsShowMinute || null != tempIsShowSecond || null != tempIsShowMillisecond) {
            var isShowDay = mCountdown.isShowDay
            if (null != tempIsShowDay) {
                isShowDay = tempIsShowDay
                mCountdown.mHasSetIsShowDay = true
            } else {
                mCountdown.mHasSetIsShowDay = false
            }
            var isShowHour = mCountdown.isShowHour
            if (null != tempIsShowHour) {
                isShowHour = tempIsShowHour
                mCountdown.mHasSetIsShowHour = true
            } else {
                mCountdown.mHasSetIsShowHour = false
            }
            val isShowMinute = tempIsShowMinute ?: mCountdown.isShowMinute
            val isShowSecond = tempIsShowSecond ?: mCountdown.isShowSecond
            val isShowMillisecond = tempIsShowMillisecond ?: mCountdown.isShowMillisecond
            val isModCountdownInterval = mCountdown.refTimeShow(isShowDay, isShowHour, isShowMinute, isShowSecond, isShowMillisecond)

            // judgement modify countdown interval
            if (isModCountdownInterval) {
                start(remainTime)
            }
            isReLayout = true
        }
        val backgroundInfo: DynamicConfig.BackgroundInfo = dynamicConfig.getBackgroundInfo()
        if (!isHideTimeBackground && null != backgroundInfo) {
            val backgroundCountdown: BackgroundCountdown = mCountdown as BackgroundCountdown
            val size: Float = backgroundInfo.getSize()
            if (null != size) {
                backgroundCountdown.setTimeBgSize(size)
                isReLayout = true
            }
            val color: Int = backgroundInfo.getColor()
            if (null != color) {
                backgroundCountdown.setTimeBgColor(color)
                isInvalidate = true
            }
            val radius: Float = backgroundInfo.getRadius()
            if (null != radius) {
                backgroundCountdown.setTimeBgRadius(radius)
                isInvalidate = true
            }
            val isShowTimeBgDivisionLine: Boolean = backgroundInfo.isShowTimeBgDivisionLine()
            if (null != isShowTimeBgDivisionLine) {
                backgroundCountdown.setIsShowTimeBgDivisionLine(isShowTimeBgDivisionLine)
                if (isShowTimeBgDivisionLine) {
                    val divisionLineColor: Int = backgroundInfo.getDivisionLineColor()
                    if (null != divisionLineColor) {
                        backgroundCountdown.setTimeBgDivisionLineColor(divisionLineColor)
                    }
                    val divisionLineSize: Float = backgroundInfo.getDivisionLineSize()
                    if (null != divisionLineSize) {
                        backgroundCountdown.setTimeBgDivisionLineSize(divisionLineSize)
                    }
                }
                isInvalidate = true
            }
            val isShowTimeBgBorder: Boolean = backgroundInfo.isShowTimeBgBorder()
            if (null != isShowTimeBgBorder) {
                backgroundCountdown.setIsShowTimeBgBorder(isShowTimeBgBorder)
                if (isShowTimeBgBorder) {
                    val borderColor: Int = backgroundInfo.getBorderColor()
                    if (null != borderColor) {
                        backgroundCountdown.setTimeBgBorderColor(borderColor)
                    }
                    val borderSize: Float = backgroundInfo.getBorderSize()
                    if (null != borderSize) {
                        backgroundCountdown.setTimeBgBorderSize(borderSize)
                    }
                    val borderRadius: Float = backgroundInfo.getBorderRadius()
                    if (null != borderRadius) {
                        backgroundCountdown.setTimeBgBorderRadius(borderRadius)
                    }
                }
                isReLayout = true
            }
        }
        val tempIsConvertDaysToHours: Boolean = dynamicConfig.isConvertDaysToHours()
        if (null != tempIsConvertDaysToHours && mCountdown.setConvertDaysToHours(tempIsConvertDaysToHours)) {
            reSetTime(remainTime)
            isReLayout = true
        }
        if (isReLayout) {
            reLayout()
        } else if (isInvalidate) {
            invalidate()
        }
    }

    fun setMaxUnit(s: String) {
        mCountdown.setMaxUnit(mCountdown.setMaxUnitFromString(s))
    }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CountdownView)
        isHideTimeBackground = ta.getBoolean(R.styleable.CountdownView_isHideTimeBackground, true)
        mCountdown = if (isHideTimeBackground) BaseCountdown() else BackgroundCountdown()
        val mMaxUnit = ta.getInt(R.styleable.CountdownView_maxUnit, 0)
        mCountdown.initStyleAttr(context, ta)
        ta.recycle()
        mCountdown.setMaxUnit(mMaxUnit)
        mCountdown.initialize()
    }
}