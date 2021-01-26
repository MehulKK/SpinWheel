package com.macv.mwheel

class WheelItem {
    var color: Int
    @JvmField
    var displayText: String? = null
    var value: Int? = null
    var currency: String? = null


    constructor(color: Int, displayText: String?, value : Int?, currency : String?) {
        this.color = color
        this.displayText = displayText
        this.value = value
        this.currency = currency
    }
}