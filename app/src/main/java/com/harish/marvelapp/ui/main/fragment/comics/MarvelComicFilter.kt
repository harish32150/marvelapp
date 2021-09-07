package com.harish.marvelapp.ui.main.fragment.comics

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

enum class MarvelComicFilter(val filterLabel: String) {
  ThisWeek("This Week"),
  NextWeek("Next Week"),
  LastWeek("Last Week"),
  ThisMonth("This Month");

  fun dateRange() =
    when (this) {
      ThisWeek -> Pair(getWeekDate(0), getWeekDate(1))
      NextWeek -> Pair(getWeekDate(1), getWeekDate(2))
      LastWeek -> Pair(getWeekDate(-1), getWeekDate(0))
      ThisMonth -> Pair(getWeekDate(0), getWeekDate(4)) /* considering month as 4weeks */
    }.let { "${it.first},${it.second}" }

  /* get starting of week date as range */
  private fun getWeekDate(week: Int) = Calendar.getInstance().apply {
    add(Calendar.WEEK_OF_YEAR, week)
  }.let { _calendar ->
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(_calendar.time)
  }

  companion object {
    /*  */
    fun fromLabel(filterLabel: String) =
      values().firstOrNull { it.filterLabel.equals(filterLabel, ignoreCase = true) }
  }
}