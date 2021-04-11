package com.macv.spinwheel

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.macv.achivement.utils.DimenUtils
import com.macv.achivement.utils.toRoundedCorners
import com.macv.mwheel.R
import kotlinx.android.synthetic.main.spin_wheel_card_componenet.view.*


class SpinWheelCardComponent @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var mMaxRewardPoints = 0
    private var mPointsPerSpin = 0
    private var DEFAULT_RADIOUS = DimenUtils.pixelsToDp(10F).toFloat()
    private var mSpinWheelCardCallBack: SpinWheelCardCallBack? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.spin_wheel_card_componenet, this, true)
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.SpinWheelCard, 0, 0)

            styledAttributes?.let {
                mMaxRewardPoints = styledAttributes.getInteger(R.styleable.SpinWheelCard_maxRewardPoints, 0)
                mPointsPerSpin = styledAttributes.getInteger(R.styleable.SpinWheelCard_pointsPerSpin, 0)
            }

            setMaxRewardPoints(mMaxRewardPoints)
            setPointsPerSpin(mPointsPerSpin)

            lytCard.setOnClickListener { mSpinWheelCardCallBack?.onSpinWheelCardClick() }

            styledAttributes.recycle()
        }

        val imgTopBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.spin_wheel_top)
        imgTopBitmap?.toRoundedCorners(bottomRightRadius = 25F)?.apply {
            // show the rounded top corners bitmap in image view
            imgTop.setImageBitmap(this)
        }
        val imgBottomBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.spin_wheel_bottom)
        imgBottomBitmap?.toRoundedCorners(bottomRightRadius = 25F)?.apply {
            // show the rounded top corners bitmap in image view
            imgBottom.setImageBitmap(this)
        }
    }


    fun setMaxRewardPoints(maxRewardPoints: Int) {
        this.mMaxRewardPoints = maxRewardPoints
        txtPoint.text = String.format(context.getString(R.string.spin_wheel_points), maxRewardPoints)
    }

    fun setPointsPerSpin(pointsPerSpin: Int) {
        this.mPointsPerSpin = pointsPerSpin
        txtDesc.text = String.format(context.getString(R.string.spin_wheel_desc), pointsPerSpin)
    }

    fun setCallBack(spinWheelCardCallBack: SpinWheelCardCallBack) {
        this.mSpinWheelCardCallBack = spinWheelCardCallBack
    }

    fun setSpinWheelCardItem(spinWheelCardComponentItem: SpinWheelCardComponentItem) {
        setMaxRewardPoints(spinWheelCardComponentItem.maxRewardPoints)
        setPointsPerSpin(spinWheelCardComponentItem.pointsPerSpin)
    }
}
