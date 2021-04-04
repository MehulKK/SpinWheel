package com.macv.sweepstakes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.macv.mwheel.R
import kotlinx.android.synthetic.main.sweepstakes_card_componenet.view.*

class SweepStakesCard @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var mTitle = ""
    private var mDescription = ""
    private var mPointsPerEntry = 0
    private var mEntry = 0
    private var mTimeStamp = 0L
    private var mMaxUnit = "DAY"

    init {
        LayoutInflater.from(context).inflate(R.layout.sweepstakes_card_componenet, this, true)
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.SweepStakesCard, 0, 0)

            styledAttributes?.let {
                mTitle = styledAttributes.getString(R.styleable.SweepStakesCard_title)
                mDescription = styledAttributes.getString(R.styleable.SweepStakesCard_description)
                mPointsPerEntry = styledAttributes.getInteger(R.styleable.SweepStakesCard_pointsPerEntry, 0)
                mEntry = styledAttributes.getInteger(R.styleable.SweepStakesCard_entries, 0)
            }

            setTitleText(mTitle)
            setDescriptionText(mDescription)
            setPointsPerEntry(mPointsPerEntry)
            setEntry(mEntry)
            styledAttributes.recycle()
        }
    }


    fun setTitleText(mTitle : String) {
        this.mTitle = mTitle
        txtTitle.text = mTitle
    }

    fun setDescriptionText(mDescription : String) {
        this.mDescription = mDescription
        txtDesc.text = mDescription
    }

    fun setPointsPerEntry(mPointsPerEntry : Int) {
        this.mPointsPerEntry = mPointsPerEntry
        txtPoint.text = String.format(context.getString(R.string.points),mPointsPerEntry)
    }

    fun setEntry(mEntry : Int) {
        this.mEntry = mEntry
        txtEntry.text = String.format(context.getString(R.string.entry),mEntry)
    }

    fun setCountDown(mTimeStamp : Long, mMaxUnit : String) {
        this.mTimeStamp = mTimeStamp
        this.mMaxUnit = mMaxUnit
        countDown.start(mTimeStamp)
        countDown.setMaxUnit(mMaxUnit)
    }

    fun setSweepStakesItem(mSweepStakesItem: SweepStakesItem){
        setTitleText(mSweepStakesItem.title)
        setDescriptionText(mSweepStakesItem.description)
        setPointsPerEntry(mSweepStakesItem.pointsPerEntry)
        setEntry(mSweepStakesItem.entires)
        setCountDown(mSweepStakesItem.timestamp, mSweepStakesItem.maxUnit)
    }
}
