package com.macv.countdown

import android.os.Handler
import android.os.Message
import android.os.SystemClock

abstract class CustomCountDownTimer(millisInFuture: Long, countDownInterval: Long) {
    private val mMillisInFuture: Long
    private var mCountdownInterval: Long = 0
    private var mStopTimeInFuture: Long = 0
    private var mPauseTimeInFuture: Long = 0
    private var isStop = false
    private var isPause = false
    @Synchronized
    private fun start(millisInFuture: Long): CustomCountDownTimer {
        isStop = false
        if (millisInFuture <= 0) {
            onFinish()
            return this
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + millisInFuture
        mHandler.sendMessage(mHandler.obtainMessage(MSG))
        return this
    }


    @Synchronized
    fun start() {
        start(mMillisInFuture)
    }


    @Synchronized
    fun stop() {
        isStop = true
        mHandler.removeMessages(MSG)
    }

    @Synchronized
    fun pause() {
        if (isStop) return
        isPause = true
        mPauseTimeInFuture = mStopTimeInFuture - SystemClock.elapsedRealtime()
        mHandler.removeMessages(MSG)
    }

    @Synchronized
    fun restart() {
        if (isStop || !isPause) return
        isPause = false
        start(mPauseTimeInFuture)
    }

    /**
     *
     * @param millisUntilFinished
     */
    abstract fun onTick(millisUntilFinished: Long)

    abstract fun onFinish()
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            synchronized(this@CustomCountDownTimer) {
                if (isStop || isPause) {
                    return
                }
                val millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime()
                if (millisLeft <= 0) {
                    onFinish()
                } else {
                    val lastTickStart = SystemClock.elapsedRealtime()
                    onTick(millisLeft)

                    // take into account user's onTick taking time to execute
                    var delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime()

                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0) delay += mCountdownInterval
                    sendMessageDelayed(obtainMessage(MSG), delay)
                }
            }
        }
    }

    companion object {
        private const val MSG = 1
    }

    /**
     * @param millisInFuture
     * @param countDownInterval
     */
    init {
        var millisInFuture = millisInFuture
        if (countDownInterval > 1000) millisInFuture += 15
        mMillisInFuture = millisInFuture
        mCountdownInterval = countDownInterval
    }
}