package pl.digitalzombielab.dayview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View


/**
 * Created by micha on 25.03.2017.
 */

class DayView : View {

    private val paint = Paint()
    private val rect = Rect()

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        canvas?.drawPaint(paint)
        drawTextCenter(canvas, paint, System.currentTimeMillis().toString())
    }

    private fun drawTextCenter(canvas: Canvas?, paint: Paint, text: String) {
        canvas?.getClipBounds(rect)
        val cHeight = rect.height()
        val cWidth = rect.width()
        paint.setTextAlign(Paint.Align.LEFT)
        paint.color = Color.BLACK
        paint.textSize = 40f
        paint.getTextBounds(text, 0, text.length, rect)
        val x = cWidth / 2f - rect.width() / 2f - rect.left
        val y = cHeight / 2f + rect.height() / 2f - rect.bottom
        canvas!!.drawText(text, x, y, paint)
    }

}