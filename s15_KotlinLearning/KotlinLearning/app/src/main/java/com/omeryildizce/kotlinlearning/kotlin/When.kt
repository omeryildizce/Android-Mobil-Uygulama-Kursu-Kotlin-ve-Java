package com.omeryildizce.kotlinlearning.kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
fun main(args: Array<String>) {

    val day = WeekDays.values()[LocalDate.now().dayOfWeek.value - 1]

    val dayInTurkish = when(day) {
        WeekDays.MONDAY -> "Pazartesi"
        WeekDays.TUESDAY -> "Salı"
        WeekDays.WEDNESDAY -> "Çarşamba"
        WeekDays.THURSDAY -> "Perşembe"
        WeekDays.FRIDAY -> "Cuma"
        WeekDays.SATURDAY -> "Cumartesi"
        WeekDays.SUNDAY -> "Pazar"
    }

    println("Bugün ${dayInTurkish}")
}


enum class WeekDays  {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
