package com.mcastro.tvmaze.domain.schedule

data class Schedule(
    val time: Time?,
    val days: List<DayOfWeek>?
) {
    override fun toString(): String {
        var text = ""

        time?.let {
            text += "$it. "
        }

        days?.let {
            text += it.joinToString(separator = ", ", postfix = ".")
        }

        return text
    }
}