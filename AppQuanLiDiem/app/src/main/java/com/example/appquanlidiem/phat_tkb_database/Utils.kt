package com.example.appquanlidiem.phat_tkb_database;

// Thời khóa biểu
// Thời khóa biểu
// Thời khóa biểu

import android.text.TextUtils
import android.widget.Toast
import androidx.cardview.widget.CardView
import java.util.*

object Utils {
    private var PATH: String? = null
    private const val SINGLE_DOUBLE_WEEK = 0
    private const val SINGLE_WEEK = 1
    private const val DOUBLE_WEEK = 2
    private val WEEK_OPTIONS = arrayOf ("Tuần", "Tuần", "Tuần")
    @JvmStatic
    fun setPATH(PATH: String?) {
        Utils.PATH = PATH
    }

    @JvmStatic
    val weekNum: Long
        get() {
            val day = System.currentTimeMillis() / (1000 * 60 * 60 * 24) - 4
            return day / 7 + 1
        }

    @JvmStatic
    fun setCardViewAlpha(cardView: CardView) {
        cardView.alpha = Config.getCardViewAlpha()
    }

    @JvmStatic
    fun getWeekOptionFromWeekOfTerm(weekOfTerm: Int): Int {
        var singleWeek: Int = 0x55555555
        var doubleWeek: Int = singleWeek.inv()
        if (Config.getMaxWeekNum() % 2 == 0) {
            val temp = singleWeek
            singleWeek = doubleWeek
            doubleWeek = temp
        }
        val hasSingleWeek = singleWeek and weekOfTerm != 0
        val hasDoubleWeek = doubleWeek and weekOfTerm != 0
        return if (hasSingleWeek && hasDoubleWeek) {
            SINGLE_DOUBLE_WEEK
        } else if (hasSingleWeek) {
            SINGLE_WEEK
        } else if (hasDoubleWeek) {
            DOUBLE_WEEK
        } else {
            -1
        }
    }

    @JvmStatic
    fun getFormatStringFromWeekOfTerm(weekOfTerm: Int): String {
        if (!isWeekOfTermValid(weekOfTerm)) {
            return "Vui lòng chọn số tuần"
        }
        return  WEEK_OPTIONS[getWeekOptionFromWeekOfTerm(weekOfTerm)] +
                " " + getStringFromWeekOfTerm(weekOfTerm)
    }

    @JvmStatic
    fun isWeekOfTermValid(weekOfTerm: Int): Boolean {
        return ((1 shl Config.getMaxWeekNum()) - 1) and weekOfTerm != 0
    }

    @JvmStatic
    fun getStringFromWeekOfTerm(weekOfTerm: Int): String {
        var weekOfTermVar = weekOfTerm
        if (weekOfTermVar == 0) {
            return ""
        }
        val stringBuilder = StringBuilder()
        val weekOptions = getWeekOptionFromWeekOfTerm(weekOfTermVar)
        val week = BooleanArray(Config.getMaxWeekNum())
        for (i in Config.getMaxWeekNum() - 1 downTo 0) {
            week[i] = weekOfTermVar and 0x01 == 1
            weekOfTermVar = weekOfTermVar shr 1
        }
        var start = 1
        var space = 2
        when (weekOptions) {
            SINGLE_DOUBLE_WEEK -> space = 1
            SINGLE_WEEK -> {
            }
            DOUBLE_WEEK -> start = 2
            else -> return "error"
        }
        var count = 0
        var i = start
        while (i <= Config.getMaxWeekNum()) {
            if (week[i - 1]) {
                if (count == 0) {
                    stringBuilder.append(i)
                }
                count += 1
            } else {
                if (count == 1) {
                    stringBuilder.append(',')
                } else if (count > 1) {
                    stringBuilder.append('-')
                    stringBuilder.append(i - space)
                    stringBuilder.append(',')
                }
                count = 0
            }
            i += space
        }
        if (count > 1) {
            stringBuilder.append('-')
            var max = Config.getMaxWeekNum()
            if (start == 1 && max % 2 == 0) {
                max--
            } else if (start == 2 && max % 2 == 1) {
                max--
            }
            stringBuilder.append(max)
        }
        val len = stringBuilder.length - 1
        if (stringBuilder[len] == ',') stringBuilder.deleteCharAt(len)
        return stringBuilder.toString()
    }

    @JvmStatic
    fun formatTime(time: Int): String {
        return if (time < 10) "0$time" else time.toString()
    }

    @JvmStatic
    val weekOfDay: Int
        get() {
            val date = Date()
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar[Calendar.DAY_OF_WEEK]
        }

    @JvmStatic
    fun showToast(text: String?) {
        showToast(text, true)
    }

    @JvmStatic
    fun showToast(text: String?, isShortLength: Boolean) {
        if (!TextUtils.isEmpty(text)) {
            val duration = if (isShortLength) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
        }
    }
}