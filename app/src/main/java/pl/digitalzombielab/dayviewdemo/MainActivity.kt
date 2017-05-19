package pl.digitalzombielab.dayviewdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import es.dmoral.coloromatic.ColorOMaticDialog
import es.dmoral.coloromatic.IndicatorMode
import es.dmoral.coloromatic.colormode.ColorMode
import pl.digitalzombielab.dayview.DayView
import java.util.*
import android.text.util.Linkify
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    var applyBtn: Button? = null
    var borderBtn: Button? = null
    var backgroundBtn: Button? = null
    var textBtn: Button? = null
    var barBtn: Button? = null
    var dayEt: EditText? = null
    var monthEt: EditText? = null
    var borderColor: Int = Color.BLACK
    var backgroundColor: Int = Color.WHITE
    var textColor: Int = Color.BLACK
    var barColor: Int = Color.BLUE
    var date: Date = Date()
    var day: Int = 1
    var month: Int = 1
    val year: Int = 2016
    var url: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dayView = findViewById(R.id.dayView) as DayView
        val dayView2 = findViewById(R.id.dayView2) as DayView
        applyBtn = findViewById(R.id.apply_btn) as Button
        borderBtn = findViewById(R.id.borderColor_btn) as Button
        backgroundBtn = findViewById(R.id.backgroundColor_btn) as Button
        textBtn = findViewById(R.id.textColor_btn) as Button
        barBtn = findViewById(R.id.barColor_btn) as Button
        dayEt = findViewById(R.id.day_et) as EditText
        monthEt = findViewById(R.id.month_et) as EditText
        url = findViewById(R.id.url_tv) as TextView
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val pattern = Pattern.compile("https://github.com/migellal/DayView")
        Linkify.addLinks(url, pattern, "")

        applyBtn!!.setOnClickListener {
            if (dayEt!!.text != null && !dayEt!!.text.isEmpty()
                    && monthEt!!.text != null && !monthEt!!.text.isEmpty()) {
                day = dayEt!!.text.toString().toInt()
                month = monthEt!!.text.toString().toInt()
                month -= 1
            }
            date = Date(year, month, day)
            dayView.date = date
            dayView.borderColor = borderColor
            dayView.cardBackgroundColor = backgroundColor
            dayView.textColor = textColor
            dayView.barColor = barColor
            dayView2.date = date
            dayView2.borderColor = borderColor
            dayView2.cardBackgroundColor = backgroundColor
            dayView2.textColor = textColor
            dayView2.barColor = barColor
        }

        borderBtn!!.setOnClickListener {
            colorPicker(borderBtn as Button)
        }

        backgroundBtn!!.setOnClickListener {
            colorPicker(backgroundBtn as Button)
        }

        textBtn!!.setOnClickListener {
            colorPicker(textBtn as Button)
        }

        barBtn!!.setOnClickListener {
            colorPicker(barBtn as Button)
        }
    }

    private fun colorPicker(btn: Button) {
        ColorOMaticDialog.Builder()
                .initialColor(Color.BLACK)
                .colorMode(ColorMode.RGB)
                .indicatorMode(IndicatorMode.HEX)
                .onColorSelected {
                    btn.setBackgroundColor(it)
                    if (btn == borderBtn)
                        borderColor = it
                    else if (btn == backgroundBtn)
                        backgroundColor = it
                    else if (btn == textBtn)
                        textColor = it
                    else
                        barColor = it
                }
                .create()
                .show(supportFragmentManager, "ColorOMaticDialog")
    }

}