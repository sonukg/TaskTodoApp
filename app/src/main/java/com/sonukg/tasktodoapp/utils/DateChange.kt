package com.sonukg.tasktodoapp.utils
import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateChange {
    fun changeFormatDate(changeFormat: String): String {
        try {
            @SuppressLint("SimpleDateFormat") val formatter: DateFormat =
                SimpleDateFormat("yyyy-MM-dd")
            @SuppressLint("SimpleDateFormat") val dateFormat =
                SimpleDateFormat("MMM dd, yyyy")
            val date = formatter.parse(changeFormat)
            return dateFormat.format(date)
        } catch (ignored: ParseException) {
        }
        return changeFormat
    }

    fun changeFormatDateToBackslice(changeFormat: String): String {
        try {
            @SuppressLint("SimpleDateFormat") val formatter: DateFormat =
                SimpleDateFormat("yyyy-MM-dd")
            @SuppressLint("SimpleDateFormat") val dateFormat =
                SimpleDateFormat("dd/MM/yyyy")
            val date = formatter.parse(changeFormat)
            return dateFormat.format(date)
        } catch (ignored: ParseException) {
        }
        return changeFormat
    }

    fun getToday(): String {
        @SuppressLint("SimpleDateFormat") val dateFormat: DateFormat =
            SimpleDateFormat("yyyy-MM-dd:HH:mm")
        return dateFormat.format(Calendar.getInstance().time)
    }

    @SuppressLint("SimpleDateFormat")
    fun getTomorrow(): String? {
        val cal: Calendar = GregorianCalendar()
        cal.add(Calendar.DATE, 1)
        return SimpleDateFormat("yyyy/MM/dd").format(cal.time)
    }

    fun getTime(): String {
        @SuppressLint("SimpleDateFormat") val dateFormat: DateFormat =
            SimpleDateFormat("HH:mm")
        return dateFormat.format(Calendar.getInstance().time)
    }
}
