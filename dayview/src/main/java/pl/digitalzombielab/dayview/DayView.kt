package pl.digitalzombielab.dayview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
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
    private var endY = 0.0F
    private var endX = 0.0F
    private var startX = 0.0F
    private var startY = 0.0F
    private var barY = 0.0F
    private var angleY = 0.0F
    private var angleX1 = 0.0F
    private var angleX2 = 0.0F
    private var monthFormat = SimpleDateFormat("M")
    private var dayFormat = SimpleDateFormat("d")

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
            day = dayFormat.format(value)
            month = getMonth(monthFormat.format(value).toInt())
            invalidate()
        }

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
        barPaint.setAntiAlias(true)
        textDayPaint.color = textColor
        textDayPaint.setTextAlign(Paint.Align.CENTER)
        textDayPaint.setAntiAlias(true)
        textMonthPaint.color = textColor
        textMonthPaint.setTextAlign(Paint.Align.CENTER)
        textMonthPaint.setAntiAlias(true)
        backgroundPaint.style = Paint.Style.FILL
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = 1F
        barPaint.style = Paint.Style.FILL
        if(day.isEmpty() && month.isEmpty()) {
            day = dayFormat.format(Date())
            month = getMonth(monthFormat.format(Date()).toInt())
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(makeBackgroundPath(), backgroundPaint)
        canvas?.drawPath(makeBorderPath(), borderPaint)
        canvas?.drawPath(makeBarLeftAnglePath(), barPaint)
        canvas?.drawPath(makeBarRightAnglePath(), barPaint)
        drawTextCenter(canvas)
    }

    override fun onSizeChanged(xNew: Int, yNew: Int, xOld: Int, yOld: Int) {
        super.onSizeChanged(xNew, yNew, xOld, yOld)
        endX = xNew.toFloat()
        endY = yNew.toFloat()
        startX = paddingLeft.toFloat()
        startY = paddingTop.toFloat()
        endX -= paddingRight
        endY -= paddingBottom
        barY = (endY - startY) / 6
        angleY = barY / 2
        angleX1 = startX + angleY
        angleX2 = endX - angleY
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun makeBarLeftAnglePath(): Path {
        val pathLeftAngle = Path()
        pathLeftAngle.moveTo(startX, barY)
        pathLeftAngle.lineTo(startX, angleY)
        pathLeftAngle.cubicTo(startX, angleY, startX, startY, angleX1, startY)
        pathLeftAngle.lineTo(angleX2 + 1, startY)
        pathLeftAngle.lineTo(angleX2 + 1, barY)
        pathLeftAngle.close()
        return pathLeftAngle
    }

    private fun makeBarRightAnglePath(): Path {
        val pathRightAngle = Path()
        pathRightAngle.moveTo(endX + 1, barY)
        pathRightAngle.lineTo(endX + 1, angleY)
        pathRightAngle.cubicTo(endX + 1, angleY, endX + 1, startY, angleX2, startY)
        pathRightAngle.lineTo(angleX2, barY)
        pathRightAngle.close()
        return pathRightAngle
    }

    private fun makeBorderPath(): Path {
        val path = Path()
        path.moveTo(startX, barY)
        path.lineTo(startX, endY)
        path.lineTo(endX, endY)
        path.lineTo(endX, barY)
        return path
    }

    private fun makeBackgroundPath(): Path {
        val path = makeBorderPath()
        path.close()
        return path
    }

    private fun drawTextCenter(canvas: Canvas?) {
        val shortMonthName = month.length <= 4
        val x = canvas!!.width.toFloat() / 2
        val y = (endY - barY) / 5
        textDayPaint.textSize = 2 * y
        canvas.drawText(day, x, barY + (2.5 * y).toFloat(), textDayPaint)
        if (shortMonthName) {
            textMonthPaint.textSize = y
            canvas.drawText(month, x, barY + (3.5 * y).toFloat(), textMonthPaint)
        } else {
            textMonthPaint.textSize = y / 2
            canvas.drawText(month, x, barY + (3.1 * y).toFloat(), textMonthPaint)
        }
    }

    private fun getMonth(month: Int): String {
        return DateFormatSymbols().months[month - 1]
    }

}