package com.mcastro.tvmaze.domain.schedule

data class Schedule(
    val time: Time,
    val days: List<DayOfWeek>
) {
    override fun toString() = "$time. ${days.joinToString(separator = ", ", postfix = ".")}"
}