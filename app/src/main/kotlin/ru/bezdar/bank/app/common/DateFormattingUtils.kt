package ru.bezdar.bank.app.common

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateFormattingUtils {
    private const val UTC = "UTC"
    private val defaultFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME.withUtcZone()

    fun Instant.formatToString(formatter: DateTimeFormatter = defaultFormatter): String {
        return formatter.format(this)
    }

    fun String.toInstant(formatter: DateTimeFormatter = defaultFormatter): Instant {
        return Instant.from(formatter.parse(this))
    }

    fun String.toLocalDate(formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE): LocalDate {
        return LocalDate.from(formatter.parse(this))
    }

    fun LocalDate.toInstant(): Instant {
        return this.atStartOfDay(ZoneId.of(UTC)).toInstant()
    }

    private fun DateTimeFormatter.withUtcZone(): DateTimeFormatter {
        return this.withZone(ZoneId.of(UTC))
    }
}
