package com.macv.achivement.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF

fun Bitmap.toRoundedCorners(
    topLeftRadius: Float = 0F,
    topRightRadius: Float = 0F,
    bottomRightRadius: Float = 0F,
    bottomLeftRadius: Float = 0F
):Bitmap?{
    val bitmap = Bitmap.createBitmap(
        width, // width in pixels
        height, // height in pixels
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)

    // the bounds of a round-rectangle to add to the path
    val rectF = RectF(0f,0f,width.toFloat(),height.toFloat())

    // float array of 8 values, 4 pairs of [x,y] radii
    val radii = floatArrayOf(
        topLeftRadius,topLeftRadius, // top left corner
        topRightRadius,topRightRadius, // top right corner
        bottomRightRadius,bottomRightRadius, // bottom right corner
        bottomLeftRadius,bottomLeftRadius // bottom left corner
    )

    // path to draw rounded corners bitmap
    val path = Path().apply {
        // add a closed round-rectangle contour to the path
        // each corner receives two radius values [x, y]
        addRoundRect(
            rectF,
            radii,
            // the direction to wind the round-rectangle's contour
            Path.Direction.CCW
        )
    }

    // intersect the current clip with the specified path
    canvas.clipPath(path)

    // draw the rounded corners bitmap on canvas
    canvas.drawBitmap(this,0f,0f,null)
    return bitmap
}
