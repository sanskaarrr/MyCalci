package com.example.mycalci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException
import android.view.View as View

class MainActivity : AppCompatActivity() {
    private var tvinput: TextView? = null
    private var deci: Boolean = false
    private var lastNum: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvinput = findViewById(R.id.tvinput)
    }

    fun OnDigit(view: View) {
        tvinput?.append((view as Button).text)
        lastNum = true

    }

    fun OnClear(view: View) {
        tvinput?.text = ""

    }

    fun OnDeci(view: View) {
        if (lastNum && !deci) {
            tvinput?.append((view as Button).text)
            deci = true
            lastNum = false
        }

    }

    fun onEquals(view: View) {
        if (lastNum) {
            var tvValue = tvinput?.text.toString()
            var pre = ""

            try {
                if (tvValue.startsWith("-")) {
                    pre = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitv = tvValue.split("-")
                    var one = splitv[0]
                    val two = splitv[1]
                    if (pre.isNotEmpty())
                        one = pre + one

                    tvinput?.text = remo((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    val splitv = tvValue.split("+")
                    var one = splitv[0]
                    val two = splitv[1]
                    if (pre.isNotEmpty())
                        one = pre + one

                    tvinput?.text = remo((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    val splitv = tvValue.split("*")
                    var one = splitv[0]
                    val two = splitv[1]
                    if (pre.isNotEmpty())
                        one = pre + one

                    tvinput?.text = remo((one.toDouble() * two.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    val splitv = tvValue.split("/")
                    var one = splitv[0]
                    val two = splitv[1]
                    if (pre.isNotEmpty())
                        one = pre + one

                    tvinput?.text = remo((one.toDouble() / two.toDouble()).toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun remo(result: String): String {
        var value = result
        if (value.contains(".0"))
           value= value.substring(0, value.length - 2)
        return value
    }

    fun onOp(view: View) {
        tvinput?.text?.let {
            if (lastNum && !isOpAdd(it.toString())) {
                tvinput?.append((view as Button).text)
                lastNum = false
                deci = false
            }
        }
    }

    private fun isOpAdd(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }


    }
}
