package pl.digitalzombielab.dayview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.util.*


/**
 * Created by micha on 25.03.2017.
 */

class DayView : View {

    private var barPaint = Paint()
    private var textDayPaint = Paint()
    private var textMonthPaint = Paint()
    private var backgroundPaint = Paint()
    private var borderPaint = Paint()
    private var day = String()
    private var month = String()
    private var height = 0.0F
    private var width = 0.0F
    private var startLeft = 0.0F
    private var startTop = 0.0F

    var barColor = -12627531
        set(value) {
            field = value
            barPaint.color = value
            invalidate()
        }
    var textColor = -16777216
        set(value) {
            field = value
            textDayPaint.color = value
            textMonthPaint.color = value
            invalidate()
        }
    var cardBackgroundColor = -1
        set(value) {
            field = value
            backgroundPaint.color = value
            invalidate()
        }
    var borderColor = -2302756
        set(value) {
            field = value
            borderPaint.color = value
            invalidate()
        }
    var date = Date()
        set(value) {
            field = value
            day = date.day.toString()
            month = date.month.toString()
            invalidate()
        }

    private val rect = Rect()

    constructor(ctx: Context) : super(ctx) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.DayView,
                0, 0)
        val typedValue = TypedValue()
        val theme = context.theme
        try {
            if (theme.resolveAttribute(R.attr.colorPrimary, typedValue, true))
                barColor = typedValue.data
            barColor = a.getColor(R.styleable.DayView_barColor, barColor)
            textColor = a.getColor(R.styleable.DayView_textColor, textColor)
            cardBackgroundColor = a.getColor(R.styleable.DayView_cardBackgroundColor, cardBackgroundColor)
            borderColor = a.getColor(R.styleable.DayView_borderColor, borderColor)
        } finally {
            a.recycle()
        }
        init()
    }

    private fun init() {
        backgroundPaint.color = cardBackgroundColor
        borderPaint.color = borderColor
        barPaint.color = barColor
        textDayPaint.color = textColor
        textMonthPaint.color = textColor
        backgroundPaint.style = Paint.Style.FILL
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = 10F
        barPaint.style = Paint.Style.FILL

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(startLeft, startTop, width, height, backgroundPaint)
        canvas?.drawRect(startLeft, startTop, width, height, borderPaint)
        canvas?.drawRect(80F, 80F, 350F, 350F, barPaint)
    }

    override fun onSizeChanged(xNew: Int, yNew: Int, xOld: Int, yOld: Int) {
        super.onSizeChanged(xNew, yNew, xOld, yOld)
        width = xNew.toFloat()
        height = yNew.toFloat()
        startLeft = paddingLeft.toFloat()
        startTop = paddingTop.toFloat()
        val xpad = (paddingLeft + paddingRight).toFloat()
        val ypad = (paddingTop + paddingBottom).toFloat()
        width -= xpad
        height -= ypad
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
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