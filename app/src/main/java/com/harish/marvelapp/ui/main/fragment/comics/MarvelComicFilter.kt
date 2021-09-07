package com.harish.marvelapp.ui.main.fragment.comics

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

enum class MarvelComicFilter(val filterLabel: String) {
  ThisWeek("This Week"),
  NextWeek("Next Week"),
  LastWeek("Last Week");

  fun dateRange() =
    when (this) {
      ThisWeek -> Pair(getWeekDateRange(0), getWeekDateRange(1))
      NextWeek -> Pair(getWeekDateRange(1), getWeekDateRange(2))
      LastWeek -> Pair(getWeekDateRange(-1), getWeekDateRange(0))
    }.let { "${it.first},${it.second}" }

  /* get starting of week date as range */
  private fun getWeekDateRange(week: Int) = Calendar.getInstance().apply {
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