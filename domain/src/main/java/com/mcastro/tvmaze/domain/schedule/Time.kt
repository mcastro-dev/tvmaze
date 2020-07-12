package com.mcastro.tvmaze.domain.schedule

import java.security.InvalidParameterException

/**
 * @param value expected "HH:mm"
 * @throws InvalidParameterException when invalid
 */
class Time(value: String) {
    companion object {
        const val MIN_HOUR = 0
        const val MAX_HOUR = 23

        const val MIN_MINUTE = 0
        const val MAX_MINUTE = 59
    }

    private val hour: Int
    private val minute: Int

    init {
        if (value.isBlank()) {
            throw InvalidParameterException()
        }

        val hour = extractHourFromValue(value)
        val minute = extractMinuteFromValue(value)
        if (hour < MIN_HOUR || hour > MAX_HOUR ||
            minute < MIN_MINUTE || minute > MAX_MINUTE) {
            throw InvalidParameterException()
        }
        this.hour = hour
        this.minute = minute
    }

    private fun extractHourFromValue(value: String) = value.substringBefore(":").toInt()

    private fun extractMinuteFromValue(value: String) = value.substringAfter(":").toInt()

    private fun intToString(number: Int) = if (number < 10) {
        "0${number}"
    } else {
        number.toString()
    }

    override fun toString() = "${intToString(hour)}:${intToString(minute)}"
}