package com.healthy.bites.basekitimpl

import com.healthy.bites.basekit.DateParser
import java.text.SimpleDateFormat
import java.util.*

/**
 * class to parse a date for a the required format for the UI, instance will be available through appcomponent
 */
class DateParserImpl : DateParser {
    override fun parseDate(startDate: String): String {
        val start = parseDateToCalender(startDate)

        return "${dayOfTheWeek(start.get(Calendar.DAY_OF_WEEK))} ${start.get(Calendar.MONTH) + 1}/${start.get(
            Calendar.DAY_OF_MONTH
        )}/${start.get(Calendar.YEAR)}"
    }

    private fun parseDateToCalender(date: String): Calendar {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = dateFormatter.parse(date)
        val myCal = GregorianCalendar()
        myCal.time = formattedDate
        return myCal
    }

    private fun dayOfTheWeek(day: Int): String {
        return when (day) {
            1 -> {
                "Sun"
            }
            2 -> {
                "Mon"
            }
            3 -> {
                "Tue"
            }
            4 -> {
                "Wed"
            }
            5 -> {
                "Thu"
            }
            6 -> {
                "Fri"
            }
            7 -> {
                "Sat"
            }
            else -> "NA"
        }
    }

    private fun amPM(amPM: Int): String {
        if (amPM == 1) return "pm"
        return "am"
    }

    private fun calculateMinute(time: Calendar): String {
        return if (time.get(Calendar.MINUTE) > 10)
            "${time.get(Calendar.MINUTE)}"
        else
            "0${time.get(Calendar.MINUTE)}"
    }
}