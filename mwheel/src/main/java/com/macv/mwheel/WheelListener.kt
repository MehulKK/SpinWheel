package com.macv.mwheel

interface WheelListener {
    fun onOpen()
    fun onClose()
    fun onReward(pos: Int)
    fun onStart()
    fun onError(s: String)
}